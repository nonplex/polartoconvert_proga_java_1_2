import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class polartoconvert extends JFrame {
    // Поля для ввода угла и радиуса
    private JTextField angleField;
    private JTextField radiusField;

    // Лейблы для отображения значений x и y
    private JLabel xLabel;
    private JLabel yLabel;

    // Кнопка для выхода из приложения
    private JButton quitButton;

    public polartoconvert() {
        // Инициализация компонентов и настройка основных свойств окна
        initComponents();
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Закрытие приложения при нажатии на крестик
        setLocationRelativeTo(null); // Центрирование окна на экране
        setVisible(true); // Показ окна
    }

    private void initComponents() {
        // Создание полей ввода угла и радиуса
        angleField = new JTextField(10);
        radiusField = new JTextField(10);

        // Создание лейблов для отображения значений x и y
        xLabel = new JLabel("x: ");
        yLabel = new JLabel("y: ");

        // Создание кнопки для выхода из приложения
        quitButton = new JButton("Quit");

        // Добавление слушателя фокуса для поля ввода угла
        angleField.addFocusListener(new FocusListener() {
            // Этот слушатель не делает ничего при получении фокуса
            public void focusGained(FocusEvent e) {
            }

            // При потере фокуса вызывается метод validateAngleInput() для проверки введенного значения угла
            public void focusLost(FocusEvent e) {
                validateAngleInput();
            }
        });

        // Добавление слушателя фокуса для поля ввода радиуса
        radiusField.addFocusListener(new FocusListener() {
            // Этот слушатель не делает ничего при получении фокуса
            public void focusGained(FocusEvent e) {
            }

            // При потере фокуса вызывается метод validateRadiusInput() для проверки введенного значения радиуса
            public void focusLost(FocusEvent e) {
                validateRadiusInput();
            }
        });

        // Добавление слушателя для кнопки выхода
        quitButton.addActionListener(new ActionListener() {
            // При нажатии на кнопку вызывается System.exit(0) для завершения работы приложения
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Создание панели для размещения компонентов
        JPanel panel = new JPanel();
        panel.add(new JLabel("Введите Угол (градусы):")); // Лейбл для поля ввода угла
        panel.add(angleField); // Поле ввода угла
        panel.add(new JLabel("Введите Радиус:")); // Лейбл для поля ввода радиуса
        panel.add(radiusField); // Поле ввода радиуса
        panel.add(xLabel); // Лейбл для отображения значения x
        panel.add(yLabel); // Лейбл для отображения значения y
        panel.add(quitButton); // Кнопка выхода

        // Добавление панели на форму
        add(panel);
        pack(); // Автоматическое изменение размера окна под содержимое
    }

    private void validateAngleInput() {
        String input = angleField.getText();
        try {
            double angle = Double.parseDouble(input);
            // Проверка введенного значения угла на корректность
            if (angle < 0 || angle > 360) {
                JOptionPane.showMessageDialog(this, "Пожалуйста, введите значение угла от 0 до 360.");
                angleField.selectAll(); // Выделение всего текста в поле ввода
                angleField.requestFocus(); // Передача фокуса полю ввода
            } else {
                updateLabels(); // Обновление лейблов с вычисленными значениями x и y
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Пожалуйста, введите корректное значение угла.");
            angleField.selectAll(); // Выделение всего текста в поле ввода
            angleField.requestFocus(); // Передача фокуса полю ввода
        }
    }

    private void validateRadiusInput() {
        String input = radiusField.getText();
        try {
            double radius = Double.parseDouble(input);
            // Проверка введенного значения радиуса на корректность
            if (radius < 0) {
                JOptionPane.showMessageDialog(this, "Пожалуйста, введите значение радиуса от 0.");
                radiusField.selectAll(); // Выделение всего текста в поле ввода
                radiusField.requestFocus(); // Передача фокуса полю ввода
            } else {
                updateLabels(); // Обновление лейблов с вычисленными значениями x и y
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Пожалуйста, введите корректное значение радиуса.");
            radiusField.selectAll(); // Выделение всего текста в поле ввода
            radiusField.requestFocus(); // Передача фокуса полю ввода
        }
    }

    private void updateLabels() {
        String angleText = angleField.getText();
        String radiusText = radiusField.getText();

        // Проверка на пустые поля ввода
        if (!angleText.isEmpty() && !radiusText.isEmpty()) {
            try {
                double angle = Double.parseDouble(angleText);
                double radius = Double.parseDouble(radiusText);

                // Проверка введенных значений угла и радиуса на корректность
                if (angle >= 0 && angle <= 360 && radius >= 0) {
                    double x = radius * Math.cos(Math.toRadians(angle)); // Вычисление значения x
                    double y = radius * Math.sin(Math.toRadians(angle)); // Вычисление значения y

                    xLabel.setText("x: " + x); // Обновление лейбла с значением x
                    yLabel.setText("y: " + y); // Обновление лейбла с значением y
                }
            } catch (NumberFormatException e) {
                // Если при преобразовании текста в числа возникла ошибка, ничего не делаем
            }
        }
    }

    public static void main(String[] args) {
        // Создание и отображение окна с помощью SwingUtilities.invokeLater()
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new polartoconvert();
            }
        });
    }
}
