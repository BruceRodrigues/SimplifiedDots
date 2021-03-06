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
import simplifiedDotsControl.Control;
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
					Control c = new Control();
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
		//+1 cause you have to convert number of cells to number of dots
		this.boardSize = boardSize+1;
		this.OFFSET = this.WH/this.boardSize;
		this.initComponents();
		this.createPoints();
	}
	
	private void initComponents() {
		this.setTitle("Simplified Dots");
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
			if(isMovimentValid(dot.position())) {
				this.controller.onClick(dot.position(), this.firstPoint);
				//TODO test
	//			this.drawLine(dot.position(), this.firstPoint);
				this.firstPoint = null;
			} else {
				invalidMovement(dot.position());
			}
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
	
	public void drawLine(Position positionA, Position positionB) {
		RadioPoint a = this.points.get(positionA);
		RadioPoint b = this.points.get(positionB);
		JSeparator line = new JSeparator();
		int x = Math.min(a.getBounds().x, b.getBounds().x);
		int y = Math.min(a.getBounds().y, b.getBounds().y);
		int w = 0; int h = 0;
		if(a.x() == b.x()) {
			line.setOrientation(SwingConstants.VERTICAL);
			h = Math.abs(a.getBounds().y - b.getBounds().y);
			w = 2;
		} else {
			line.setOrientation(SwingConstants.HORIZONTAL);
			h = 2;
			w = Math.abs(a.getBounds().x - b.getBounds().x);
		}
		line.setBounds(x,y,w,h);
		line.setForeground(Color.RED);
		this.contentPane.add(line);
		this.contentPane.repaint();
	}
	
	public boolean isMovimentValid(Position pa) {
		if(((pa.x() == this.firstPoint.x()+1 ) && (pa.y() == this.firstPoint.y() )) || 
				((pa.x() == this.firstPoint.x()-1 ) && (pa.y() == this.firstPoint.y() )) ||
				((pa.x() == this.firstPoint.x() ) && (pa.y() == this.firstPoint.y()+1 )) ||
				((pa.x() == this.firstPoint.x() ) && (pa.y() == this.firstPoint.y()-1 ))) {
			return true;
		}
		return false;
	}
	
}
