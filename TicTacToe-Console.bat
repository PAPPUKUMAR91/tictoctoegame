@echo off
title TicTacToe Console Game
echo ========================================
echo    TicTacToe Console Game Launcher
echo ========================================
echo.
echo Starting Console Game...
echo Please wait while the game loads...
echo.
cd /d E:\JAVAPROJECT
"C:\Users\pappu\Downloads\OpenJDK17U-jdk_x64_windows_hotspot_17.0.16_8 (1)\jdk-17.0.16+8\bin\javac.exe" TicTacToe.java
if %errorlevel% neq 0 (
    echo Error: Compilation failed!
    pause
    exit /b 1
)
echo Compilation successful! Starting game...
"C:\Users\pappu\Downloads\OpenJDK17U-jdk_x64_windows_hotspot_17.0.16_8 (1)\jdk-17.0.16+8\bin\java.exe" -cp . TicTacToe
echo Game closed.
pause
