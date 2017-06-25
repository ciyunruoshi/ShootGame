package com.airplace.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.airpalnegame.main.Bullet;
import com.airpalnegame.main.Hero;
import com.airpalnegame.main.airplane;
import com.airpalnegame.main.bee;

public class shootgame extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public shootgame() {
	}

	public static final int WIDTH = 400; // 面板宽  
    public static final int HEIGHT = 654; // 面板高 
    
    private static int status=0;
    private static final int START = 0;  
    private static final int RUNNING = 1;  
    private static final int PAUSE = 2;  
    private static final int GAME_OVER = 3;
    
    Vector<Bullet> bullets = new Vector<Bullet>();
    Iterator<Bullet> tb;
    Vector<bee> bees = new Vector<bee>();
    Iterator<bee> te;
    Vector<airplane> airs = new Vector<airplane>();
    Iterator<airplane> ta;
    Timer timer = new Timer();
    int delay =1000/100;
    public static final int as= 1;
    public static final int bs= 5;
    boolean FLAG=false;
    public int enemy_total=0;
    
    
    private Hero hero  = new Hero();;
    
    
    public static BufferedImage background;  
    public static BufferedImage start;  
    public static BufferedImage airplaneimage;  
    public static BufferedImage beeimage;  
    public static BufferedImage bulletimage;  
    public static BufferedImage hero0;  
    //public static BufferedImage hero1;  
    public static BufferedImage pause;  
    public static BufferedImage gameover;  
    
	static{
		try {
		background = ImageIO.read(new File("background.png"));
        start = ImageIO.read(new File("start.png"));  
        airplaneimage = ImageIO.read(new File("airplane.png"));  
        beeimage = ImageIO.read(new File("bee.png"));  
        bulletimage = ImageIO.read(new File("bullet.png"));  
        hero0 = ImageIO.read(new File("hero0.png"));  
        //hero1 = ImageIO.read(shootgame.class.getResource("hero1.png"));  
        pause = ImageIO.read(new File("pause.png"));  
        
		gameover = ImageIO.read(new File("gameover.png"));
		} catch (IOException e) {
			
			e.printStackTrace();
		} 
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					  JFrame frame = new JFrame("Fly");  
						   shootgame game = new shootgame(); // 面板对象  
						    frame.add(game); // 将面板添加到JFrame中  
						    frame.setSize(WIDTH, HEIGHT); // 设置大小  
						    frame.setAlwaysOnTop(true); // 设置其总在最上  
						    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 默认关闭操作  
						    //frame.setIconImage(new ImageIcon("images/icon.jpg").getImage()); // 设置窗体的图标  
						    frame.setLocationRelativeTo(null); // 设置窗体初始位置  
						    frame.setVisible(true); // 尽快调用paint 
						    
						    game.action();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
//	public shootgame() {
//		
//
//	}

	@Override
	public void paint(Graphics g) {
		
		g.drawImage(background, 0, 0, null);
		paintairplane(g);
		paintbullet(g);
		paintbee(g);
		painthero(g);
		paintscore(g);
		painterror(g);
		paintstate(g); // 画游戏状态 
	}

	int fs=0;
	public void painterror(Graphics g){
		if(fs<=3&&FLAG==true&&status==RUNNING){
			g.setColor(new Color(255, 0, 0,100));
			g.fillRect(0, 0, WIDTH, HEIGHT);
			FLAG=false;
			fs++;
		}else{
			fs=0;
		}
	}
	public void paintairplane(Graphics g) {
		ta = airs.iterator();
		while(ta.hasNext()){
			airplane temp = (airplane)ta.next();
			g.drawImage(shootgame.airplaneimage, temp.getX(), temp.getY(), null);
		}

	}

	public void paintbullet(Graphics g) {
		tb = bullets.iterator();
		while(tb.hasNext()){
			Bullet b2 = (Bullet)tb.next();
			g.drawImage(shootgame.bulletimage, b2.getX(), b2.getY(), null);
		}
	}

	public void paintbee(Graphics g) {
		te=bees.iterator();
		while(te.hasNext()){
			bee b2 = te.next();
			g.drawImage(shootgame.beeimage, b2.getX(), b2.getY(), null);
		}
	}

	public void painthero(Graphics g) {
		
		g.drawImage(hero0, hero.getX(), hero.getY(), null);
		
	}
	
	public void paintscore(Graphics g) {
		int x=10;
		int y = 20;
		g.setColor(Color.red);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		g.drawString("Total: "+hero.gettotalgrade(), x, y);
		y +=30;
		g.drawString("HP: "+hero.getlife(), x, y);
		y +=30;
		g.drawString("EP: "+enemy_total, x, y);
	}
	

	public void paintstate(Graphics g) {
		switch (status) {
		case START:
			g.drawImage(start, 0, 0, null);
			break;
       case PAUSE:
    	   g.drawImage(pause, 0, 0, null);
			break;
       case GAME_OVER:
    	   g.drawImage(gameover, 0, 0, null);
			break;
		}
	}
	
	public void action(){
		
		MouseAdapter mouse1=new MouseAdapter() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mouseMoved(MouseEvent e) {
				if(status == RUNNING){
				int x = e.getX();
				int y= e.getY();
				hero.moveTo(x, y);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				 
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(status == RUNNING){
					status= PAUSE;
				}
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				if(status == PAUSE){
					status= RUNNING;
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				switch (status) {
				case START:
					status = RUNNING;
					break;

				case PAUSE:
					status = RUNNING;
					break;
				case RUNNING:
					status = PAUSE;
					break;
				case GAME_OVER:
					status = START;
					bees.clear();
					airs.clear();
					bullets.clear();
					hero = new Hero();
					break;
				}
			}
		};
		this.addMouseListener(mouse1);
		this.addMouseMotionListener(mouse1);
		
		timer =new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				if(status == RUNNING){
					enterAction(); // 飞行物入场  
                    stepAction(); // 走一步  
                    shootAction(); // 英雄机射击  
                    bangAction(); // 子弹打飞行物  
                    outOfBoundsAction(); // 删除越界飞行物及子弹  
                    checkGameOverAction(); // 检查游戏结束
				}
				repaint();
				
			}
		}, delay,delay);
	}
	int index =0;
	int index2 =0;
	public void enterAction(){
		index++;
		index2++;
		if(index>=20){
			airplane newair = new airplane();
			airs.add(newair);
			index =0;
		}
		if(index2>=70){
			bee newbee = new bee();
			bees.add(newbee);
			index2 =0;
		}
		
		
	}
	public void stepAction(){
		ta= airs.iterator();
		while(ta.hasNext()){
			ta.next().step();
		}
		te= bees.iterator();
		while(te.hasNext()){
			te.next().step();
		}
		tb= bullets.iterator();
		while(tb.hasNext()){
			tb.next().step();
		}
	}
	int shoot =0;
	public void shootAction(){
		shoot++;
		if(shoot>=20){
			Bullet[] b=hero.shootbullet();
			for(int i=0;i<b.length;i++){
				bullets.add(b[i]);
			}
			shoot=0;
		}

	}
	
	public void bangAction(){
		tb= bullets.iterator();
		while(tb.hasNext()){
			if(bang((Bullet)tb.next())){
				tb.remove();
			}
		}
		
		
	}
	
	public boolean bang(Bullet b){
		ta= airs.iterator();
		while(ta.hasNext()){
			airplane temp =(airplane)ta.next();
			if(temp.isshoot(b)){
				System.out.println("shoot");
				hero.addgrade(as);
				ta.remove();
				return true;
			}
		}
		
		te= bees.iterator();
		while(te.hasNext()){
			bee tt2 =(bee)te.next();
			if(tt2.isshoot(b)){
				hero.addgrade(bs);
				System.out.println("shootbee");
				if(tt2.getaward()==0){
					hero.addfire();
				}else{
					hero.addlife();
				}
				te.remove();
				return true;
			}
		}
		return false;
	}
	
	 public void outOfBoundsAction(){
			ta= airs.iterator();
			while(ta.hasNext()){
				if(((airplane)ta.next()).overbound()){
					ta.remove();
					enemy_total++;
				}
			}
			te= bees.iterator();
			while(te.hasNext()){
				if(((bee)te.next()).overbound()){
					enemy_total++;
					te.remove();
				}
			}
			tb= bullets.iterator();
			while(tb.hasNext()){
				if(((Bullet)tb.next()).overbound()){
					tb.remove();
				}
			}
	 }
	
	public void checkGameOverAction(){
		ta= airs.iterator();
		while(ta.hasNext()){
			airplane herottoair = (airplane)ta.next();
			if(hero.hit(herottoair)){
				hero.substractlife();
				System.out.println("装机一次");
				hero.subfire();
				FLAG = true;
				ta.remove();
			}
		}
		te= bees.iterator();
		while(te.hasNext()){
			bee herotobee = (bee)te.next();
			if(hero.hit(herotobee)){
				hero.substractlife();
				System.out.println("撞蜜蜂一次");
				hero.subfire();
				FLAG = true;
				te.remove();
			}
		}
		if(hero.getlife()<=0){
			status = GAME_OVER;
		}
		if(enemy_total>=10){
			status = GAME_OVER;
		}
	} 
	
}
