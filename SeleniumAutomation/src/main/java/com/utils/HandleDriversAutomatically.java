package com.utils;

import org.apache.commons.io.FileDeleteStrategy;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class HandleDriversAutomatically {
    ReadProperties readProperties = ReadProperties.getInstance();

    private static HandleDriversAutomatically instance = null;

    public static HandleDriversAutomatically getInstance() {
        if (instance == null)
            instance = new HandleDriversAutomatically();
        return instance;
    }

    public String getInstalledBrowserVersion() {
        Process browserProcess;
        String version = "";
        BufferedReader bufferedReader;
        try {
            switch (readProperties.getPropValue("browser")) {
                case "CHROME":
                    browserProcess = Runtime.getRuntime().exec("powershell -command \"&{(Get-Item 'C:/Program Files/Google/Chrome/Application/chrome.exe').VersionInfo.ProductVersion}\"");
                    bufferedReader = new BufferedReader(new InputStreamReader(browserProcess.getInputStream()));
                    while ((version = bufferedReader.readLine()) != null) {
                        return version;
                    }
                    break;
                case "EDGE":
                    browserProcess = Runtime.getRuntime().exec("powershell -command \"&{(Get-Item 'C:/Program Files (x86)/Microsoft/Edge/Application/msedge.exe').VersionInfo.ProductVersion}\"");
                    bufferedReader = new BufferedReader(new InputStreamReader(browserProcess.getInputStream()));
                    while ((version = bufferedReader.readLine()) != null) {
                        return version;
                    }
                    break;
                default:
                    System.out.println("Browser does not exist");
            }
        } catch (Exception ex) {
            System.out.println("Browser not found");
        }
        return version;
    }


    public String getLatestStableChromeDriverVersion() {
        URL url;
        String stableDriverVersion;
        HttpURLConnection httpURLConnection;
        String response = "";
        Scanner scanner;
        try {
            url = new URL("https://googlechromelabs.github.io/chrome-for-testing/last-known-good-versions-with-downloads.json");

            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 200) {
                scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    response += scanner.nextLine();
                }
            }
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(response);
            JSONObject obj = (JSONObject) jsonObject.get("channels");
            JSONObject stableVersion = (JSONObject) obj.get("Stable");
            stableDriverVersion = stableVersion.get("version").toString();
            return stableDriverVersion;
        } catch (Exception ex) {

        }
        return "";
    }

    public void downloadLatestChromeDriver() {
        String systemType;
        URL url;
        Path filePath = Path.of(System.getProperty("user.dir") + Paths.get("/Drivers") + File.separator);
        String version = getLatestStableChromeDriverVersion();
        if (version.contains(getInstalledChromeDriverVersion())) {
            System.out.println("Already latest version of driver is installed as per the browser installed in the system");
        } else {
            try {
                systemType = readProperties.getPropValue("systemType");
                if (SystemUtils.IS_OS_WINDOWS) {
                    url = new URL("https://storage.googleapis.com/chrome-for-testing-public/" + version + "/win" + systemType + "/chromedriver-win" + systemType + ".zip");
                    if (Files.exists(filePath)) {
                        new FileOutputStream(filePath + "/ChromeDriver.zip").getChannel().transferFrom(Channels.newChannel(url.openStream()), 0, Long.MAX_VALUE);
                    } else {
                        File dir = new File(String.valueOf(filePath));
                        dir.mkdirs();
                        new FileOutputStream(filePath + "/ChromeDriver.zip").getChannel().transferFrom(Channels.newChannel(url.openStream()), 0, Long.MAX_VALUE);
                    }
                    unzipFolder(Path.of(filePath + "/ChromeDriver.zip"), Path.of(filePath + "/ChromeDriver/"));

                }
                if (SystemUtils.IS_OS_LINUX) {
                    url = new URL("https://storage.googleapis.com/chrome-for-testing-public/" + version + "/linux64/chromedriver-linux64.zip");

                    if (Files.exists(filePath)) {
                        new FileOutputStream(filePath + "/ChromeDriver.zip").getChannel().transferFrom(Channels.newChannel(url.openStream()), 0, Long.MAX_VALUE);
                    } else {
                        File dir = new File(String.valueOf(filePath));
                        dir.mkdirs();
                    }
                    Path source = Path.of(filePath + "/ChromeDriver.zip");
                    unzipFolder(source, Path.of(String.valueOf(filePath) + "/ChromeDriver/"));

                }
            } catch (Exception ex) {

            }
        }
    }

    public void unzipFolder(Path source, Path target) throws IOException {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(source.toFile()))) {
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                boolean isDirectory = false;
                if (zipEntry.getName().endsWith(File.separator)) {
                    isDirectory = true;
                }
                Path newPath = zipSlipProtect(zipEntry, target);
                if (isDirectory) {
                    Files.createDirectories(newPath);
                } else {
                    if (newPath.getParent() != null) {
                        if (Files.notExists(newPath.getParent())) {
                            Files.createDirectories(newPath.getParent());
                        }
                    }
                    Files.copy(zis, newPath, StandardCopyOption.REPLACE_EXISTING);
                }
                zipEntry = zis.getNextEntry();
            }
            zis.closeEntry();
        }
    }

    // protect zip slip attack
    public Path zipSlipProtect(ZipEntry zipEntry, Path targetDir)
            throws IOException {
        Path targetDirResolved = targetDir.resolve(zipEntry.getName());
        Path normalizePath = targetDirResolved.normalize();
        if (!normalizePath.startsWith(targetDir)) {
            throw new IOException("Bad zip entry: " + zipEntry.getName());
        }

        return normalizePath;
    }

    public String getInstalledChromeDriverVersion() {
        Process driverProcess;
        String driverVersion = "";
        String systemType = "64";
        BufferedReader bufferedReader;
        String[] tempVersion;
        StringTokenizer stringTokenizer;
        Path filePath = Path.of(System.getProperty("user.dir") + Paths.get("/Drivers/ChromeDriver") + File.separator);
        if (Files.exists(filePath)) {
            try {
                driverProcess = Runtime.getRuntime().exec(System.getProperty("user.dir") + Paths.get("/Drivers/ChromeDriver") + "/chromedriver-win" + systemType + "/chromedriver.exe -version");
                bufferedReader = new BufferedReader(new InputStreamReader(driverProcess.getInputStream()));
                while ((driverVersion = bufferedReader.readLine()) != null) {
                    tempVersion = driverVersion.split(" ");
                    driverVersion = tempVersion[1];
                    stringTokenizer = new StringTokenizer(driverVersion, ".");
                    return stringTokenizer.nextToken();
                }
            } catch (Exception ex) {

            }
        }
        return "does not exist";
    }


}
