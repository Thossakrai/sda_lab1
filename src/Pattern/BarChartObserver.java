package Pattern;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
 
import javax.swing.JPanel;
 
/**
 * This class represents a bar chart view of a vector of data. Uses the Observer
 * pattern.
 */
@SuppressWarnings("serial")
public class BarChartObserver extends JPanel implements Observer {
	/**
	 * Creates a BarChartObserver object
	 * 
	 * @param data
	 *            a CourseData object to observe
	 */
	public BarChartObserver(CourseData data) {
		data.attach(this);
		this.courseData = data.getUpdate();
		this.setPreferredSize(new Dimension(2 * LayoutConstants.xOffset
				+ (LayoutConstants.barSpacing + LayoutConstants.barWidth)
				* this.courseData.size(), LayoutConstants.graphHeight + 2
				* LayoutConstants.yOffset));
		this.setBackground(Color.white);
	}

	/**
	 * Paint method
	 * 
	 * @param g
	 *            a Graphics object on which to paint
	 */
	public void paint(Graphics g) {
		super.paint(g);
		LayoutConstants.paintBarChartOutline(g, this.courseData.size());
		for (int i = 0; i < courseData.size(); i++) {
			CourseRecord record = (CourseRecord) courseData.get(i);
			g.setColor(Color.blue);
			g.fillRect(LayoutConstants.xOffset + (i + 1)
							* LayoutConstants.barSpacing + i
							* LayoutConstants.barWidth, LayoutConstants.yOffset
							+ LayoutConstants.graphHeight
							- LayoutConstants.barHeight
							+ 2
							* (LayoutConstants.maxValue - record
									.getNumOfStudents()),
			LayoutConstants.barWidth, 2 * record.getNumOfStudents());
			g.setColor(Color.red);
			g.drawString(record.getName(),
					LayoutConstants.xOffset + (i + 1)
							* LayoutConstants.barSpacing + i
							* LayoutConstants.barWidth, LayoutConstants.yOffset
							+ LayoutConstants.graphHeight + 20);
		}
	// Pie Chart
//		double total = 0.0;
//		int radius = 100;
//		for (int i = 0; i < this.courseData.size(); i++) {
//			CourseRecord record = (CourseRecord) courseData.get(i);
//			total += record.getNumOfStudents();
//		}
//		//if total == 0 nothing to draw
//		if (total != 0) {
//			double startAngle = 0.0;
//			for (int i = 0; i < this.courseData.size(); i++) {
//				CourseRecord record = (CourseRecord) courseData.get(i);
//				double ratio = (record.getNumOfStudents() / total) * 360.0;
//				//draw the arc
//				g.setColor(Nonpattern.LayoutConstants.courseColours[i % Nonpattern.LayoutConstants.courseColours.length]);
//				g.fillArc(Nonpattern.LayoutConstants.xOffset, Nonpattern.LayoutConstants.yOffset + 300, 2 * radius, 2 * radius, (int) startAngle, (int) ratio);
//				startAngle += ratio;
//			}
//		}
	}

	/**
	 * Informs this observer that the observed CourseData object has changed
	 * 
	 * @param o
	 *            the observed CourseData object that has changed
	 */
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

	private ArrayList<CourseRecord> courseData;
}