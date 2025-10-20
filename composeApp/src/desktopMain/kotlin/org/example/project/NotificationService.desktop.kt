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

                // Создаем простую иконку (можно заменить на вашу)
                val image = Toolkit.getDefaultToolkit().createImage("")
                val trayIcon = TrayIcon(image, "Schedule App")
                trayIcon.isImageAutoSize = true

                // Добавляем в трей
                tray.add(trayIcon)

                // ПОКАЗЫВАЕМ УВЕДОМЛЕНИЕ
                trayIcon.displayMessage(title, message, TrayIcon.MessageType.INFO)

                // Удаляем через 5 секунд
                Thread {
                    Thread.sleep(5000)
                    tray.remove(trayIcon)
                }.start()
            } else {
                // Fallback для систем без трея
                println("🔔 $title: $message")
            }
        } catch (e: Exception) {
            // Если что-то пошло не так, хотя бы выводим в консоль
            println("🔔 $title: $message")
            e.printStackTrace()
        }
    }
}