/* Copyright (c) 2007 Timothy Wall, All Rights Reserved
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 */
package com.sun.jna.platform.win32;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.IntByReference;

/**
 * Ported from Wincon.h.
 * @author lgoldstein
 */
public interface Wincon {
    /**
     * Allocates a new console for the calling process.
     * @return {@code true} if successful - if {@code false} then use
     * {@code GetLastError()} to get extended error information
     * @see <A HREF="https://msdn.microsoft.com/en-us/library/ms681944(v=vs.85).aspx">AllocConsole</A>
     * documentation
     */
    boolean AllocConsole();

    /**
     * Detaches the calling process from its console
     * @return {@code true} if successful - if {@code false} then use
     * {@code GetLastError()} to get extended error information
     * @see <A HREF="https://msdn.microsoft.com/en-us/library/ms683150(v=vs.85).aspx">FreeConsole</A>
     * documentation
     */
    boolean FreeConsole();
    
    int ATTACH_PARENT_PROCESS=(-1);
    /**
     * Attaches the calling process to the console of the specified process
     * @param dwProcessId The identifier of the process whose console is to
     * be used. Can be either the process ID or the special {@link #ATTACH_PARENT_PROCESS}
     * value to indicate the console of the parent of the current process.
     * @return {@code true} if successful - if {@code false} then use
     * {@code GetLastError()} to get extended error information
     * @see <A HREF="https://msdn.microsoft.com/en-us/library/ms681952(v=vs.85).aspx">AttachConsole</A>
     * documentation
     */
    boolean AttachConsole(int dwProcessId);

    /**
     * Flushes the console input buffer. All input records currently in the input
     * buffer are discarded.
     * @param hConsoleInput A handle to the console input buffer. The handle must
     * have the GENERIC_WRITE access right.
     * @return {@code true} if successful - if {@code false} then use
     * {@code GetLastError()} to get extended error information
     * @see <A HREF="https://msdn.microsoft.com/en-us/library/ms683147(v=vs.85).aspx">FlushConsoleInputBuffer</A>
     * documentation
     */
    boolean FlushConsoleInputBuffer(HANDLE hConsoleInput);

    /* Events to be sent by GenerateConsoleCtrlEvent */
    int CTRL_C_EVENT=0;
    int CTRL_BREAK_EVENT=1;

    /**
     * Sends a specified signal to a console process group that shares the console
     * associated with the calling process.
     * @param dwCtrlEvent The type of signal to be generated.
     * @param dwProcessGroupId The identifier of the process group to receive the signal
     * @return {@code true} if successful - if {@code false} then use
     * {@code GetLastError()} to get extended error information
     * @see <A HREF="https://msdn.microsoft.com/en-us/library/ms683155(v=vs.85).aspx">GenerateConsoleCtrlEvent</A>
     * documentation
     */
    boolean GenerateConsoleCtrlEvent(int dwCtrlEvent, int dwProcessGroupId);

    /**
     * @return Code that identifies the code page
     * @see <A HREF="https://msdn.microsoft.com/en-us/library/dd317756(v=vs.85).aspx">Code page identifiers</A>
     */
    int GetConsoleCP();

    /**
     * @param wCodePageID The identifier of the code page to be set
     * @return {@code true} if successful - if {@code false} then use
     * {@code GetLastError()} to get extended error information
     * @see <A HREF="https://msdn.microsoft.com/en-us/library/ms686013(v=vs.85).aspx">SetConsoleCP</A>
     * documentation
     */
    boolean SetConsoleCP(int wCodePageID);

    /**
     * @return Retrieves the output code page used by the console associated with the calling process.
     * @see <A HREF="https://msdn.microsoft.com/en-us/library/dd317756(v=vs.85).aspx">Code page identifiers</A>
     */
    int GetConsoleOutputCP();

    /**
     * @param wCodePageID The output code page used by the console associated with the calling process.
     * @return {@code true} if successful - if {@code false} then use
     * {@code GetLastError()} to get extended error information
     * @see <A HREF="https://msdn.microsoft.com/en-us/library/ms686036(v=vs.85).aspx">SetConsoleOutputCP</A>
     * documentation
     */
    boolean SetConsoleOutputCP(int wCodePageID);

    /**
     * @return The window handle used by the console associated with the calling process
     */
    HWND GetConsoleWindow();

    /**
     * @param hConsoleInput A handle to the console input buffer. The handle must
     * have the GENERIC_READ access right
     * @param lpcNumberOfEvents A  pointer to a variable that receives the number
     * of unread input records in the console's input buffer
     * @return {@code true} if successful - if {@code false} then use
     * {@code GetLastError()} to get extended error information
     * @see <A HREF="https://msdn.microsoft.com/en-us/library/ms683207(v=vs.85).aspx">GetNumberOfConsoleInputEvents</A>
     * documentation
     */
    boolean GetNumberOfConsoleInputEvents(HANDLE hConsoleInput, IntByReference lpcNumberOfEvents);

    /**
     * @param lpNumberOfMouseButtons A pointer to a variable that receives the number
     * of mouse buttons
     * @return {@code true} if successful - if {@code false} then use
     * {@code GetLastError()} to get extended error information
     * @see <A HREF="https://msdn.microsoft.com/en-us/library/ms683208(v=vs.85).aspx">GetNumberOfConsoleMouseButtons</A>
     * documentation
     */
    boolean GetNumberOfConsoleMouseButtons(IntByReference lpNumberOfMouseButtons);

    /* The values to use for Get/SetStdHandle */
    int STD_INPUT_HANDLE=(-10);
    int STD_OUTPUT_HANDLE=(-11);
    int STD_ERROR_HANDLE=(-12);

    /**
     * @param nStdHandle The standard device identifier
     * @return A handle to the specified standard device (standard input, output, or error)
     */
    HANDLE GetStdHandle(int nStdHandle);

    /**
     * @param nStdHandle The standard device identifier
     * @param hHandle The handle for the standard device
     * @return {@code true} if successful - if {@code false} then use
     * {@code GetLastError()} to get extended error information
     * @see <A HREF="https://msdn.microsoft.com/en-us/library/ms686244(v=vs.85).aspx">SetStdHandle</A>
     * documentation
     */
    boolean SetStdHandle(int nStdHandle, HANDLE hHandle);

    /* console mode values */
    int CONSOLE_FULLSCREEN=1;
    int CONSOLE_FULLSCREEN_HARDWARE=2;

    /**
     * Retrieves the display mode of the current console
     * @param lpModeFlags The display mode of the console
     * @return {@code true} if successful - if {@code false} then use
     * {@code GetLastError()} to get extended error information
     * @see <A HREF="https://msdn.microsoft.com/en-us/library/ms683164(v=vs.85).aspx">GetConsoleDisplayMode</A>
     * documentation
     */
    boolean GetConsoleDisplayMode(IntByReference lpModeFlags);
    
    /* console modes used by Get/SetConsoleMode */
    int ENABLE_PROCESSED_INPUT=0x0001;
    int ENABLE_LINE_INPUT=0x0002;
    int ENABLE_ECHO_INPUT=0x0004;
    int ENABLE_WINDOW_INPUT=0x0008;
    int ENABLE_MOUSE_INPUT=0x0010;
    int ENABLE_INSERT_MODE=0x0020;
    int ENABLE_QUICK_EDIT_MODE=0x0040;
    int ENABLE_EXTENDED_FLAGS=0x0080;

    /* If the hConsoleHandle parameter is a screen buffer handle, the mode
     * can be one or more of the following values
     */
    int ENABLE_PROCESSED_OUTPUT=0x0001;
    int ENABLE_WRAP_AT_EOL_OUTPUT=0x0002;

    /**
     * @param hConsoleHandle A handle to the console input buffer or the console
     * screen buffer. The handle must have the GENERIC_READ access right
     * @param lpMode A pointer to a variable that receives the current mode of
     * the specified buffer
     * @return {@code true} if successful - if {@code false} then use
     * {@code GetLastError()} to get extended error information
     * @see <A HREF="https://msdn.microsoft.com/en-us/library/ms683167.aspx">GetConsoleMode</A>
     * documentation
     */
    boolean GetConsoleMode(HANDLE hConsoleHandle, IntByReference lpMode);

    /**
     * @param hConsoleHandle A handle to the console input buffer or a
     * console screen buffer. The handle must have the GENERIC_READ access right
     * @param dwMode The input or output mode mask to be set
     * @return {@code true} if successful - if {@code false} then use
     * {@code GetLastError()} to get extended error information
     * @see <A HREF="https://msdn.microsoft.com/en-us/library/ms686033.aspx">SetConsoleMode</A>
     * documentation
     */
    boolean SetConsoleMode(HANDLE hConsoleHandle, int dwMode);
    
    int MAX_CONSOLE_TITLE_LENGTH=64 * 1024;

    /**
     * @param lpConsoleTitle A pointer to a buffer that receives a null-terminated
     * string containing the title. If the buffer is too small to store the title,
     * the function stores as many characters of the title as will fit in the buffer,
     * ending with a null terminator. <B>Note:</B> use {@link Native#toString(char[])}
     * to convert it to a {@link String} value
     * @param nSize The size of the buffer pointed to by the lpConsoleTitle parameter,
     * in characters.
     * @return If the function succeeds, the return value is the length of the console
     * window's title, in characters. If the function fails, the return value is zero
     * and {@code GetLastError} returns the error code.
     * @see <A HREF="https://msdn.microsoft.com/en-us/library/windows/desktop/ms683174(v=vs.85).aspx">GetConsoleTitle</A>
     * documentation
     */
    int GetConsoleTitle(char[] lpConsoleTitle, int nSize);

    /**
     * @param lpConsoleTitle A pointer to a buffer that receives a null-terminated
     * string containing the original title. <B>Note:</B> use {@link Native#toString(char[])}
     * to convert it to a {@link String} value
     * @param nSize The size of the lpConsoleTitle buffer, in characters
     * @return If the function succeeds, the return value is the length of the
     * string copied to the buffer, in characters. If the buffer is not large enough
     * to store the title, the return value is zero and {@code GetLastError} returns
     * {@code ERROR_SUCCESS}. If the function fails, the return value is zero
     * and {@code GetLastError} returns the error code.
     * @see <A HREF="https://msdn.microsoft.com/en-us/library/windows/desktop/ms683168(v=vs.85).aspx">GetConsoleOriginalTitle</A>
     * documentation
     */
    int GetConsoleOriginalTitle(char[] lpConsoleTitle, int nSize);

    /**
     * @param lpConsoleTitle The string to be displayed in the title bar of the console window.
     * The total size must be less than {@link #MAX_CONSOLE_TITLE_LENGTH}.
     * @return {@code true} if successful - if {@code false} then use
     * {@code GetLastError()} to get extended error information
     * @see <A HREF="https://msdn.microsoft.com/en-us/library/ms686050(v=vs.85).aspx">SetConsoleTitle</A>
     * documentation
     */
    boolean SetConsoleTitle(String lpConsoleTitle);
}
