package quiz.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Quiz extends JFrame implements ActionListener {

    String questions[][] = new String[10][5];
    String answers[][] = new String[10][1]; // store correct option letter here ("A", "B", "C", or "D")
    String useranswers[][] = new String[10][1];
    JLabel qno, question;
    JRadioButton opt1, opt2, opt3, opt4;
    ButtonGroup groupoptions;
    JButton next, submit, lifeline;

    int timer = 15;
    int ans_given = 0;
    int count = 0;
    int score = 0;

    String name;

    Timer swingTimer; // Swing timer for countdown

    Quiz(String name) {
        this.name = name;

        setSize(1220, 720);
        setLocationRelativeTo(null); // Center on screen
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/quiz.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 1220, 320);
        add(image);

        qno = new JLabel();
        qno.setBounds(80, 360, 50, 30);
        qno.setFont(new Font("Tahoma", Font.PLAIN, 24));
        add(qno);

        question = new JLabel();
        question.setBounds(130, 360, 900, 30);
        question.setFont(new Font("Tahoma", Font.PLAIN, 24));
        add(question);

        loadQuestionsFromDB();

        opt1 = new JRadioButton();
        opt1.setBounds(130, 430, 700, 30);
        opt1.setBackground(Color.WHITE);
        opt1.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(opt1);

        opt2 = new JRadioButton();
        opt2.setBounds(130, 470, 700, 30);
        opt2.setBackground(Color.WHITE);
        opt2.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(opt2);

        opt3 = new JRadioButton();
        opt3.setBounds(130, 510, 700, 30);
        opt3.setBackground(Color.WHITE);
        opt3.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(opt3);

        opt4 = new JRadioButton();
        opt4.setBounds(130, 550, 700, 30);
        opt4.setBackground(Color.WHITE);
        opt4.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(opt4);

        groupoptions = new ButtonGroup();
        groupoptions.add(opt1);
        groupoptions.add(opt2);
        groupoptions.add(opt3);
        groupoptions.add(opt4);

        next = new JButton("Next");
        next.setBounds(900, 460, 200, 40);
        next.setFont(new Font("Tahoma", Font.PLAIN, 22));
        next.setBackground(new Color(30, 144, 255));
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        add(next);

        lifeline = new JButton("50-50 Lifeline");
        lifeline.setBounds(900, 530, 200, 40);
        lifeline.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lifeline.setBackground(new Color(30, 144, 255));
        lifeline.setForeground(Color.WHITE);
        lifeline.addActionListener(this);
        add(lifeline);

        submit = new JButton("Submit");
        submit.setBounds(900, 600, 200, 40);
        submit.setFont(new Font("Tahoma", Font.PLAIN, 22));
        submit.setBackground(new Color(30, 144, 255));
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setEnabled(false);
        add(submit);

        // Initialize and start timer using Swing Timer (fires every 1 second)
        swingTimer = new Timer(1000, e -> {
            timer--;
            if (timer >= 0) {
                repaint();
            }
            if (timer < 0) {
                timer = 15;
                ans_given = 1; // Force next question
                saveUserAnswer();
                if (count == 9) {
                    swingTimer.stop();
                    calculateScore();
                    setVisible(false);
                    new Score(name, score);
                } else {
                    count++;
                    if (count == 9) {
                        next.setEnabled(false);
                        submit.setEnabled(true);
                    }
                    start(count);
                }
            }
        });
        swingTimer.start();

        start(count);

        setVisible(true);
    }

    private void loadQuestionsFromDB() {
        try {
            Connection con = connectDb.getConnection();
            String query = "SELECT question_text, option_a, option_b, option_c, option_d, correct_option " +
                    "FROM questions ORDER BY RAND() LIMIT 10";

            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            int index = 0;
            while (rs.next() && index < 10) {
                questions[index][0] = rs.getString("question_text");
                questions[index][1] = rs.getString("option_a");
                questions[index][2] = rs.getString("option_b");
                questions[index][3] = rs.getString("option_c");
                questions[index][4] = rs.getString("option_d");

                // Store correct option letter ("A", "B", "C", or "D")
                char correctOpt = rs.getString("correct_option").charAt(0);
                answers[index][0] = String.valueOf(correctOpt);

                index++;
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading questions from database.\n" + e.getMessage());
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == next) {
            saveUserAnswer();
            count++;
            if (count == 9) {
                next.setEnabled(false);
                submit.setEnabled(true);
            }
            start(count);
            timer = 15;
            ans_given = 0;

            opt1.setEnabled(true);
            opt2.setEnabled(true);
            opt3.setEnabled(true);
            opt4.setEnabled(true);

        } else if (ae.getSource() == lifeline) {
            if (count == 2 || count == 4 || count == 6 || count == 8 || count == 9) {
                opt2.setEnabled(false);
                opt3.setEnabled(false);
            } else {
                opt1.setEnabled(false);
                opt4.setEnabled(false);
            }
            lifeline.setEnabled(false);
        } else if (ae.getSource() == submit) {
            saveUserAnswer();
            calculateScore();
            swingTimer.stop();
            setVisible(false);
            new Score(name, score);
        }
    }

    private void saveUserAnswer() {
        ans_given = 1;
        if (groupoptions.getSelection() == null) {
            useranswers[count][0] = "";
        } else {
            useranswers[count][0] = groupoptions.getSelection().getActionCommand();
        }
    }

    private void calculateScore() {
        score = 0;
        for (int i = 0; i < useranswers.length; i++) {
            if (useranswers[i][0] != null && useranswers[i][0].equals(answers[i][0])) {
                score += 10;
            }
        }
    }

    public void paint(Graphics g) {
        super.paint(g);

        String time = "Time left - " + timer + " seconds";
        g.setColor(Color.RED);
        g.setFont(new Font("Tahoma", Font.BOLD, 25));

        if (timer > 0) {
            g.drawString(time, 900, 410);
        } else {
            g.drawString("Times up!!", 900, 410);
        }
    }

    public void start(int count) {
        qno.setText("" + (count + 1) + ". ");
        question.setText(questions[count][0]);
        opt1.setText(questions[count][1]);
        opt1.setActionCommand("A");  // set letter code

        opt2.setText(questions[count][2]);
        opt2.setActionCommand("B");

        opt3.setText(questions[count][3]);
        opt3.setActionCommand("C");

        opt4.setText(questions[count][4]);
        opt4.setActionCommand("D");

        groupoptions.clearSelection();

        // Enable all options when starting new question
        opt1.setEnabled(true);
        opt2.setEnabled(true);
        opt3.setEnabled(true);
        opt4.setEnabled(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Quiz("User"));
    }
}
