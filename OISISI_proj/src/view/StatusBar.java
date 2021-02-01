package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.GlobalConstants;

public class StatusBar extends JPanel {
	
	public StatusBar() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(10,23));
		
		JLabel lab = new JLabel("  " + GlobalConstants.mfName);
		
		
		DateFormat timeFormat = new SimpleDateFormat("HH:mm dd.MM.yyyy.  ");
		
		String currentTime = timeFormat.format(Calendar.getInstance().getTime());
		JLabel timeLab = new JLabel(currentTime);
		
		
		ActionListener timerListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Date date = new Date(System.currentTimeMillis());
                String time = timeFormat.format(date);
                timeLab.setText(time);
            }
        };
		
        Timer timer = new Timer(0, timerListener);
        timer.setInitialDelay(0);
        timer.start();
        
		lab.setFont(lab.getFont().deriveFont(16f));
		timeLab.setFont(lab.getFont().deriveFont(16f));		
		
		add(lab,BorderLayout.WEST);
		add(timeLab,BorderLayout.EAST);
		
	}
}
