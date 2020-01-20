package Pattern;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PieChartObserver extends JPanel implements Observer {
    private ArrayList<CourseRecord> courseData;

    public PieChartObserver(CourseData data) {
        data.attach(this);
        this.courseData = data.getUpdate();
        this.setPreferredSize(new Dimension(2 * LayoutConstants.xOffset
                + (LayoutConstants.barSpacing + LayoutConstants.barWidth)
                * this.courseData.size(), LayoutConstants.graphHeight + 2
                * LayoutConstants.yOffset));
        this.setBackground(Color.white);
    }

    public void paint(Graphics g) {
        super.paint(g);
        int radius = 100;

        //first compute the total number of students
        double total = 0.0;
        for (int i = 0; i < this.courseData.size(); i++) {
            CourseRecord record = (CourseRecord) courseData.get(i);
            total += record.getNumOfStudents();
        }
        //if total == 0 nothing to draw
        if (total != 0) {
            double startAngle = 0.0;
            for (int i = 0; i < this.courseData.size(); i++) {
                CourseRecord record = (CourseRecord) courseData.get(i);
                double ratio = (record.getNumOfStudents() / total) * 360.0;
                //draw the arc
                g.setColor(Nonpattern.LayoutConstants.courseColours[i % Nonpattern.LayoutConstants.courseColours.length]);
                g.fillArc(Nonpattern.LayoutConstants.xOffset, Nonpattern.LayoutConstants.yOffset + 300, 2 * radius, 2 * radius, (int) startAngle, (int) ratio);
                startAngle += ratio;
            }
        }
    }

    public void update(Observable o) {
        CourseData data = (CourseData) o;
        this.courseData = data.getUpdate();

        this.setPreferredSize(new Dimension(2 * LayoutConstants.xOffset
                + (LayoutConstants.barSpacing + LayoutConstants.barWidth)
                * this.courseData.size(), LayoutConstants.graphHeight + 2
                * LayoutConstants.yOffset));
        this.revalidate();
        this.repaint();
    }

    public void update(Observable o, Object obj) {
        CourseData data = (CourseData) o;
        this.courseData = data.getUpdate();

        this.setPreferredSize(new Dimension(2 * LayoutConstants.xOffset
                + (LayoutConstants.barSpacing + LayoutConstants.barWidth)
                * this.courseData.size(), LayoutConstants.graphHeight + 2
                * LayoutConstants.yOffset));
        this.revalidate();
        this.repaint();
    }
}
