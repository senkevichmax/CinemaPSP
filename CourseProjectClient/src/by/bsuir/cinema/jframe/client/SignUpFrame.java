package by.bsuir.cinema.jframe.client;

        import static by.bsuir.cinema.controller.Starter.user;

        import by.bsuir.cinema.controller.Starter;
        import by.bsuir.cinema.exception.ProjectException;
        import by.bsuir.cinema.logic.UserLogic;
        import by.bsuir.cinema.util.Encryption;

        import javax.swing.*;
        import java.io.DataOutputStream;
        import java.io.IOException;

public class SignUpFrame {
    public JPanel signUpPanel;
    private JTextField loginText;
    private JButton signUp;
    private JLabel password;
    private JLabel login;
    private JLabel signInLogic;
    private JPasswordField passwordField;
    private JFrame jFrame;
    private DataOutputStream output;

    public SignUpFrame(JFrame frame, DataOutputStream dataOutputStream){
        this.jFrame = frame;
        output = dataOutputStream;

        signUp.addActionListener(e -> {
            try {
                output.writeUTF("signUp&"+loginText.getText() + "&" + passwordField.getText());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Вы успешно зарегистрировались");
            this.jFrame.dispose();
            openAuthFrame();
            /*try {
               if (!UserLogic.isLoginExists(loginText.getText())){
                    //user = UserLogic.registerNewClient(loginText.getText(),
                      //      Encryption.encryptPassword(passwordField.getText()));
                    JOptionPane.showMessageDialog(null, "Вы успешно зарегистрировались");
                   // output.writeUTF(user.toString() + " зарегистрировался");
                    this.jFrame.dispose();
                    openAuthFrame();
                } else {
                    JOptionPane.showMessageDialog(null, "Такой логин уже существует, попробуйте " +
                            "другой");
                    output.writeUTF("Неудачная попытка регистрации: " + loginText.getText());
                }
            } catch (ProjectException e1) {
                JOptionPane.showMessageDialog(null, "Упс, что-то пошло не так:(");
                e1.printStackTrace();
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(null, "Упс, что-то пошло не так:(");
                e1.printStackTrace();
            }*/
        });
    }

    private void openAuthFrame(){
        JFrame authorizationFrame = new JFrame("АВТОРИЗАЦИЯ");
        authorizationFrame.setBounds(650, 300, 1000, 300);
        authorizationFrame.setContentPane(new Starter(authorizationFrame, output).authorizationPanel);
        authorizationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        authorizationFrame.pack();
        authorizationFrame.setVisible(true);
    }
}
