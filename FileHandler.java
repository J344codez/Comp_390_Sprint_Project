package com.example.sprint_1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

// /Storage for user profiles and daily fitness logs.
//  Profiles go in users.txt; each user has a <username>_logs.txt file.
public class FileHandler {

    private final Path dataDir;
    private final Path usersFile;
    // Earlier shared-file path; saveLogs() uses per-user files instead.
    private final Path dailyLogsFile;

    // Choose the storage folder, e.g. new FileHandler(Path.of("data")).
    // This constructor prepares paths; the save methods create the folder.
    public FileHandler(Path dataDir) {
        this.dataDir = dataDir;
        this.usersFile = dataDir.resolve("users.txt");
        this.dailyLogsFile = dataDir.resolve("dailyLogs.txt");
    }

    // Create the folder if needed
    public void ensureDataDirExists() throws IOException {
        Files.createDirectories(dataDir);
    }

    // Current weight is restored from the latest log once logs are loaded.
    private String userToLine(User user) {
        return user.getUserName() + "|" +
               user.getRegDate() + "|" +
               user.fName() + "|" +
               user.getlName() + "|" +
               user.getAge() + "|" +
               user.getGender() + "|" +
               user.getHeight() + "|" +
               user.getStartWeight() + "|" +
               user.getTargetWeight();
    }

    // This version rejects null or empty lists.
    public void saveUsers(List<User> users) throws IOException {
        if (users == null || users.isEmpty()) {
            throw new IllegalArgumentException("Users list cannot be null or empty.");
        }

        List<String> lines = new ArrayList<>();

        for (User user : users) {
            if (user == null) {
                throw new IllegalArgumentException("User cannot be null.");
            }

            // Prevent the username from breaking the field or line separators.
            String username = user.getUserName();
            if (username == null || username.isBlank() ||
                username.contains("|") ||
                username.contains("\n") ||
                username.contains("\r")) {
                throw new IllegalArgumentException("Invalid username.");
            }

            lines.add(userToLine(user));
        }

        ensureDataDirExists();
        Files.write(usersFile, lines, StandardCharsets.UTF_8);
    }

    // Rebuild a profile using the same field.
    private User lineToUser(String line) {
        // Escape the pipe separator; -1 keeps empty fields.
        String[] fields = line.split("\\|", -1);

        if (fields.length != 9) {
            throw new IllegalArgumentException("Invalid user line format.");
        }

        return new User(
            fields[0],
            LocalDate.parse(fields[1]),
            fields[2],
            fields[3],
            Integer.parseInt(fields[4]),
            fields[5],
            Double.parseDouble(fields[6]),
            Double.parseDouble(fields[7]),
            Double.parseDouble(fields[8])
        );
    }

    // Load profiles only; their log collections start empty.
    public List<User> loadUsers() throws IOException {
        List<User> users = new ArrayList<>();
        List<String> lines;

        try {
            lines = Files.readAllLines(usersFile, StandardCharsets.UTF_8);
        } catch (NoSuchFileException e) {
            return users;
        }

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);

            if (line.isBlank()) {
                continue;
            }

            try {
                users.add(lineToUser(line));
            } catch (IllegalArgumentException | DateTimeParseException e) {
                throw new IOException(
                    "Invalid user record on line " + (i + 1), e
                );
            }
        }

        return users;
    }

    // Replace this user's file with their COMPLETE log collection.
    // An empty collection clears the file.
    public void saveLogs(User user) throws IOException {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }

        // Keep filenames simple and prevent path separators in usernames.
        String username = user.getUserName();
        if (username == null || !username.matches("[a-zA-Z0-9_-]+")) {
            throw new IllegalArgumentException(
                "Username must contain only letters, numbers, underscores, or hyphens."
            );
        }

        List<String> lines = new ArrayList<>();
        for (DailyLog log : user.getLogs()) {
            lines.add(logToLine(log));
        }

        ensureDataDirExists();
        Path logFile = dataDir.resolve(username + "_logs.txt");
        Files.write(logFile, lines, StandardCharsets.UTF_8);
    }

    // Store absent workout/duration values as empty fields.
    // BMI and calories burned are recalculated when a DailyLog is rebuilt.
    private String logToLine(DailyLog log) {
        String workout = log.getWorkout() == null
                ? ""
                : log.getWorkout().name();

        String duration = log.getWoDuration() == null
                ? ""
                : log.getWoDuration().toString();

        return log.getDate() + "|" +
               log.getWeight() + "|" +
               log.getSteps() + "|" +
               log.getCalsConsumed() + "|" +
               workout + "|" +
               duration;
    }

    // Rebuild one log for the supplied owner
    // This helper will be used by the future log-loading method.
    private DailyLog lineToLog(String line, User user) {
        String[] fields = line.split("\\|", -1);

        if (fields.length != 6) {
            throw new IllegalArgumentException("Invalid log line format.");
        }

        LocalDate date = LocalDate.parse(fields[0]);
        double weight = Double.parseDouble(fields[1]);
        int steps = Integer.parseInt(fields[2]);
        int calories = Integer.parseInt(fields[3]);

        // Convert empty optional fields back to null.
        DailyLog.Activity workout = fields[4].isEmpty()
                ? null
                : DailyLog.Activity.valueOf(fields[4]);

        Integer duration = fields[5].isEmpty()
                ? null
                : Integer.valueOf(fields[5]);

        return new DailyLog(
            user, date, weight, steps, calories, workout, duration
        );
    }

    public Path getDataDir() {
        return dataDir;
    }

    public Path getUsersFile() {
        return usersFile;
    }

    // Returns the earlier shared-file path, not a user's actual log-file path.
    public Path getDailyLogsFile() {
        return dailyLogsFile;
    }
}
 