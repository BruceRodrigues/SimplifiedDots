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

import simplifiedDots.InvalidMovementException;
import simplifiedDots.Position;

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
	private int chosenPoints;
	private Position firstPoint;
	
	private Map<Position,RadioPoint> points;
	
	public Window(int boardSize) {
		this.chosenPoints = 0;
		this.boardSize = boardSize;
		this.WH = 400;
		this.boardSize = boardSize;
		this.OFFSET = this.WH/this.boardSize;
		this.points = new HashMap<Position,RadioPoint>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 401, 402);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		this.createPoints();
		
	}
	
	private void createPoints() {
		for (int i = 0; i < this.boardSize; i++) {
			for (int j = 0; j < this.boardSize; j++) {
				Position position = new Position(j,i);
				RadioPoint radioPoint = new RadioPoint(j,i);
				radioPoint.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						((RadioPoint)e.getSource()).setSelected(true);
						Window.this.dotClick((RadioPoint)e.getSource());
					}
				});
				radioPoint.setBounds(position.y() * this.OFFSET, position.x() * this.OFFSET, 20,20);
				this.contentPane.add(radioPoint);
				this.points.put(position, radioPoint);
			}
		}
	}
	
	private void dotClick(RadioPoint dot) {
		if(Window.this.chosenPoints == 1) {
			
			try {
				this.drawLine(dot, this.points.get(this.firstPoint));
			} catch (InvalidMovementException ex) {
				dot.setSelected(false);
				this.points.get(this.firstPoint).setSelected(false);
			}
			
			Window.this.firstPoint = null;
		} else {
			Window.this.firstPoint = new Position (dot.x(),dot.y());
		}
		Window.this.chosenPoints = (Window.this.chosenPoints + 1) % 2;  
	}
	
	private void drawLine(RadioPoint a, RadioPoint b) throws InvalidMovementException{
		if(!areNeighboors(a.position(), b.position())) {
			throw new InvalidMovementException();
		}
		JSeparator line = new JSeparator();
		if(a.x() == b.x()) {
			line.setOrientation(SwingConstants.HORIZONTAL);
			if(a.y() < b.y()) {
				line.setBounds(a.getBounds().x, a.getBounds().y, b.getBounds().x - a.getBounds().x, 2);
			} else {
				line.setBounds(b.getBounds().x, b.getBounds().y, a.getBounds().x - b.getBounds().x, 2);
			}
		} else {
			line.setOrientation(SwingConstants.VERTICAL);
			if(a.x() < b.x()) {
				line.setBounds(a.getBounds().x, a.getBounds().y, 2,b.getBounds().y - a.getBounds().y);
			} else {
				line.setBounds(b.getBounds().x, b.getBounds().y, 2,a.getBounds().y - b.getBounds().y);
			}
		}
		line.setForeground(Color.RED);
		this.contentPane.add(line);
		this.contentPane.repaint();
		
	}
	
	private boolean areNeighboors(Position a, Position b) {
		if( ( (a.x()+1 == b.x()) && (a.y() == b.y() ) ) || 
			( (a.x()-1 == b.x()) && (a.y() == b.y() ) ) || 
			( (a.y()+1 == b.y()) && (a.x() == b.x() ) ) ||
			( (a.y()-1 == b.y()) && (a.x() == b.x() ) ) ) {
			return true;
		}
		return false;
	}
}
