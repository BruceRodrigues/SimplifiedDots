package simplifiedDotsGUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import simplifiedDots.Position;
import simplifiedDotsControl.IControl;

public class Window extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Window frame = new Window(5);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * This Class represents the dots of the game.
	 * @author brrodrigues
	 *
	 */
	public class RadioPoint extends JRadioButton {
		
		private static final long serialVersionUID = 1L;
		private int x;
		private int y;
		private Position position;
		
		public RadioPoint(int x, int y) {
			super();
			this.x = x;
			this.y = y;
			this.position = new Position(this.x,this.y);
		}
		public int x() { return this.x; }
		public int y() { return this.y; }
		public Position position() { return this.position; }
	}

	/**
	 * Create the frame.
	 */
	private int boardSize;
	private final int OFFSET;
	private final int WH;
	private Position firstPoint;
	
	private Map<Position,RadioPoint> points;
	
	private IControl controller;
	
	public Window(int boardSize) {
		this.WH = 400;
		this.boardSize = boardSize;
		this.OFFSET = this.WH/this.boardSize;
		this.initComponents();
		this.createPoints();
	}
	
	private void initComponents() {
		this.points = new HashMap<Position,RadioPoint>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, this.WH, this.WH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	
	/**
	 * Create the board's dots.
	 */
	private void createPoints() {
		for (int i = 0; i < this.boardSize; i++) {
			for (int j = 0; j < this.boardSize; j++) {
				RadioPoint radioPoint = new RadioPoint(i,j);
				radioPoint.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						((RadioPoint)e.getSource()).setSelected(true);
						Window.this.click((RadioPoint)e.getSource());
					}
				});
				radioPoint.setBounds(radioPoint.position().x() * this.OFFSET, radioPoint.position().y() * this.OFFSET, 20,20);
				this.contentPane.add(radioPoint);
				this.points.put(radioPoint.position(), radioPoint);
			}
		}
	}
	
	public void addListener(IControl controller) {
		this.controller = controller;
	}
	
	private void click(RadioPoint dot) {
		if(this.firstPoint != null) {
			this.controller.onClick(dot.position(), this.firstPoint);
			this.firstPoint = null;
		} else {
			this.firstPoint = dot.position();
		}
	}
	
	public void invalidMovement(Position position) {
		RadioPoint a = this.points.get(position);
		RadioPoint b = this.points.get(this.firstPoint);
		a.setSelected(false);
		b.setSelected(false);
	}
	
	public void drawLine(Position position) {
		RadioPoint a = this.points.get(position);
		RadioPoint b = this.points.get(this.firstPoint);
		JSeparator line = new JSeparator();
		if(a.x() == b.x()) {
			line.setOrientation(SwingConstants.VERTICAL);
			if(a.y() < b.y()) {
				line.setBounds(a.getBounds().x, a.getBounds().y, 2,b.getBounds().y - a.getBounds().y);
			} else {
				line.setBounds(b.getBounds().x, b.getBounds().y, 2,a.getBounds().y - b.getBounds().y);
			}
		} else {
			line.setOrientation(SwingConstants.HORIZONTAL);
			if(a.x() < b.x()) {
				line.setBounds(a.getBounds().x, a.getBounds().y,b.getBounds().x - a.getBounds().x,2);
			} else {
				line.setBounds(b.getBounds().x, b.getBounds().y,a.getBounds().x - b.getBounds().x,2);
			}
		}
		line.setForeground(Color.RED);
		this.contentPane.add(line);
		this.contentPane.repaint();
	}
	
}
