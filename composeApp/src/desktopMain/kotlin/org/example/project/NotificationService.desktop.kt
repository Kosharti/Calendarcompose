package org.example.project

import java.awt.SystemTray
import java.awt.Toolkit
import java.awt.TrayIcon
import javax.swing.ImageIcon

actual class NotificationService actual constructor(private val context: Any?) {
    actual fun showNotification(title: String, message: String) {
        showDesktopNotification(title, message)
    }

    actual fun showReminderNotification(title: String, message: String) {
        showDesktopNotification("🔔 $title", message)
    }

    private fun showDesktopNotification(title: String, message: String) {
        try {
            if (SystemTray.isSupported()) {
                val tray = SystemTray.getSystemTray()

                val image = Toolkit.getDefaultToolkit().createImage("")
                val trayIcon = TrayIcon(image, "Schedule App")
                trayIcon.isImageAutoSize = true

                tray.add(trayIcon)

                trayIcon.displayMessage(title, message, TrayIcon.MessageType.INFO)

                Thread {
                    Thread.sleep(5000)
                    tray.remove(trayIcon)
                }.start()
            } else {
                println("🔔 $title: $message")
            }
        } catch (e: Exception) {
            println("🔔 $title: $message")
            e.printStackTrace()
        }
    }
}