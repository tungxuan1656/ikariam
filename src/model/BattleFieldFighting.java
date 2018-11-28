package model;

import java.awt.Window;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import model.unit.Archer;
import model.unit.BalloonBombardier;
import model.unit.Catapult;
import model.unit.Gyrocopter;
import model.unit.Hoplite;
import model.unit.Mortar;
import model.unit.Ram;
import model.unit.Slinger;
import model.unit.Spearman;
import model.unit.SteamGiant;
import model.unit.SulphurCarabineer;
import model.unit.Swordsman;
import ui.BattleFieldFightingUI;
import ui.IsLandUI;
import ui.WaveAttackUI;

/**
 *
 * @author GBlue
 */
public class BattleFieldFighting {

    private BattleField attackBattleField, defenceBattleField;
    private boolean war;
    private int houseID;

    public BattleFieldFighting(int houseID) {
        this.houseID = houseID;
        war = false;
    }

    public void startAttack() {
    	// đặt thời gian bắt đầu vào hiệp
    	HashMap<Integer, ArrayList<SentArmy>> sentRealArmy;
    	sentRealArmy = IsLandUI.myHouse.getSentRealArmy();
    	sentRealArmy.forEach((key, arrList) -> {
            for (int j = 0; j < arrList.size(); j++) {
                SentArmy sentArmy = arrList.get(arrList.size() - j - 1);
                sentArmy.setArrivalTime(System.currentTimeMillis() + WaveAttackUI.timeRound);
//                System.out.println(sentArmy.getArrivalTime());
            }
        });
//        int totalHitpointDefenceBefore = defenceBattleField.getTotalHitpoint();
        attackBattleField.makeArtilleryAttack(defenceBattleField);
        attackBattleField.makeAirDefenseAttack(defenceBattleField);
        attackBattleField.makeBombersAttack(defenceBattleField);
        attackBattleField.makeFlanksAttack(defenceBattleField);
        attackBattleField.makeFrontLineAttack(defenceBattleField);
        attackBattleField.makeLongRangeAttack(defenceBattleField);
        defenceBattleField.makeArtilleryAttack(attackBattleField);
        defenceBattleField.makeAirDefenseAttack(attackBattleField);
        defenceBattleField.makeBombersAttack(attackBattleField);
        defenceBattleField.makeFlanksAttack(attackBattleField);
        defenceBattleField.makeFrontLineAttack(attackBattleField);
        defenceBattleField.makeLongRangeAttack(attackBattleField);
        
//        int totalHitpointDefenceAfter = defenceBattleField.getTotalHitpoint();
        if (IsLandUI.bffUI == null) {
            IsLandUI.bffUI = new BattleFieldFightingUI(this);
        } else {
            IsLandUI.bffUI.resetBattleFieldFightingUI(this);
        }
        Timer timer = new Timer();
        int houseID = this.houseID;
        
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
//                if(totalHitpointDefenceAfter == totalHitpointDefenceBefore && totalHitpointDefenceBefore != 0) {
//            	if (false) {
//                    JOptionPane.showMessageDialog(null, "Enemy "+IsLandUI.house[houseID].getName()+" won con cac");
//                }
//                else {
                
                    attackBattleField.setUnitsBackToReserve();
                    defenceBattleField.setUnitsBackToReserve();

                    // chạy vào vòng for này khi mà ấn waveattack, thì cái mảng kia mới có phần tử
                    // đang đánh mà ấn lui quân thì sẽ code sẽ đi vào đây
                    // nếu ko phải đang đánh thì nó là cái ở bên kia
	                for (int j = 0; j < IsLandUI.house[houseID].getWaitingWaveAttack().size(); j++) {
	                	System.out.println("run 2");
	                    Army army = new Army();
	                    RealArmy realArmy = IsLandUI.house[houseID].getWaitingWaveAttack().get(j);
	
	                    for (int i = 0; i < realArmy.getArcher().size(); i++) {
	                        if (attackBattleField.reserve.getArcher().contains(realArmy.getArcher().get(i))) {
	                            Archer archer = realArmy.getArcher().get(i);
	                            attackBattleField.reserve.getArcher().remove(archer);
	                            army.setNumberOfArcher(army.getNumberOfArcher() + 1);
	                        }
	                    }
	
	                    for (int i = 0; i < realArmy.getBB().size(); i++) {
	                        if (attackBattleField.reserve.getBB().contains(realArmy.getBB().get(i))) {
	                            BalloonBombardier bb = realArmy.getBB().get(i);
	                            attackBattleField.reserve.getBB().remove(bb);
	                            army.setNumberOfBB(army.getNumberOfBB() + 1);
	                        }
	                    }
	                    for (int i = 0; i < realArmy.getCatapult().size(); i++) {
	                        if (attackBattleField.reserve.getCatapult().contains(realArmy.getCatapult().get(i))) {
	                            Catapult catapult = realArmy.getCatapult().get(i);
	                            attackBattleField.reserve.getCatapult().remove(catapult);
	                            army.setNumberOfCatapult(army.getNumberOfCatapult() + 1);
	                        }
	                    }
	                    for (int i = 0; i < realArmy.getGyrocopter().size(); i++) {
	                        if (attackBattleField.reserve.getGyrocopter().contains(realArmy.getGyrocopter().get(i))) {
	                            Gyrocopter gy = realArmy.getGyrocopter().get(i);
	                            attackBattleField.reserve.getGyrocopter().remove(gy);
	                            army.setNumberOfGyrocopter(army.getNumberOfGyrocopter() + 1);
	                        }
	                    }
	                    for (int i = 0; i < realArmy.getHop().size(); i++) {
	                        if (attackBattleField.reserve.getHop().contains(realArmy.getHop().get(i))) {
	                            Hoplite hop = realArmy.getHop().get(i);
	                            attackBattleField.reserve.getHop().remove(hop);
	                            army.setNumberOfHop(army.getNumberOfHop() + 1);
	                        }
	                    }
	                    for (int i = 0; i < realArmy.getMortar().size(); i++) {
	                        if (attackBattleField.reserve.getMortar().contains(realArmy.getMortar().get(i))) {
	                            Mortar mortar = realArmy.getMortar().get(i);
	                            attackBattleField.reserve.getMortar().remove(mortar);
	                            army.setNumberOfMotar(army.getNumberOfMotar() + 1);
	                        }
	                    }
	                    for (int i = 0; i < realArmy.getRam().size(); i++) {
	                        if (attackBattleField.reserve.getRam().contains(realArmy.getRam().get(i))) {
	                            Ram ram = realArmy.getRam().get(i);
	                            attackBattleField.reserve.getRam().remove(ram);
	                            army.setNumberOfRam(army.getNumberOfRam() + 1);
	                        }
	                    }
	                    for (int i = 0; i < realArmy.getSC().size(); i++) {
	                        if (attackBattleField.reserve.getSC().contains(realArmy.getSC().get(i))) {
	                            SulphurCarabineer sul = realArmy.getSC().get(i);
	                            attackBattleField.reserve.getSC().remove(sul);
	                            army.setNumberOfSC(army.getNumberOfSC() + 1);
	                        }
	                    }
	                    for (int i = 0; i < realArmy.getSlinger().size(); i++) {
	                        if (attackBattleField.reserve.getSlinger().contains(realArmy.getSlinger().get(i))) {
	                            Slinger slinger = realArmy.getSlinger().get(i);
	                            attackBattleField.reserve.getSlinger().remove(slinger);
	                            army.setNumberOfSlinger(army.getNumberOfSlinger() + 1);
	                        }
	                    }
	                    for (int i = 0; i < realArmy.getSpear().size(); i++) {
	                        if (attackBattleField.reserve.getSpear().contains(realArmy.getSpear().get(i))) {
	                            Spearman spear = realArmy.getSpear().get(i);
	                            attackBattleField.reserve.getSpear().remove(spear);
	                            army.setNumberOfSpear(army.getNumberOfSpear() + 1);
	                        }
	                    }
	                    for (int i = 0; i < realArmy.getSteam().size(); i++) {
	                        if (attackBattleField.reserve.getSteam().contains(realArmy.getSteam().get(i))) {
	                            SteamGiant steam = realArmy.getSteam().get(i);
	                            attackBattleField.reserve.getSteam().remove(steam);
	                            army.setNumberOfSteam(army.getNumberOfSteam() + 1);
	                        }
	                    }
	                    for (int i = 0; i < realArmy.getSword().size(); i++) {
	                        if (attackBattleField.reserve.getSword().contains(realArmy.getSword().get(i))) {
	                            Swordsman sword = realArmy.getSword().get(i);
	                            attackBattleField.reserve.getSword().remove(sword);
	                            army.setNumberOfSword(army.getNumberOfSword() + 1);
	                        }
	                    }
	                    
	                    // cái này là thời gian lồn gì đây
	                    // hóa ra là thời gian khi mà ấn wave attack thì nó mới vào đây
	                    // ấn vào khi trong trận rồi, đang đánh nhau
	                    Timer timer1 = new Timer();
	                    timer1.schedule(new TimerTask() {
	                        @Override
	                        public void run() {
	                            IsLandUI.myHouse.getArmy().addArmy(army);
	                            JOptionPane.showMessageDialog(null, "Wave Success!");
	                        }
	                    }, Integer.parseInt((String)IsLandUI.cbbTimeWave.getSelectedItem())*1000); // thời gian wave attack
	                    sentRealArmy.forEach((key, arrList) -> {
	                        for (int k = 0; k < arrList.size(); k++) {
	                            SentArmy sentArmy = arrList.get(arrList.size() - k - 1);
	                            sentArmy.setArrivalTime(System.currentTimeMillis() + Integer.parseInt((String)IsLandUI.cbbTimeWave.getSelectedItem())*1000);
	                            sentArmy.isdel = true;
	                        }
	                    });
	                }
                
	                IsLandUI.house[houseID].getWaitingWaveAttack().clear();
	                
	                
	                // khi thua

	                if (attackBattleField.isAllDead()) {
//	                	System.out.println("attack");
	                    war = false;
	                    IsLandUI.house[houseID].getArmy().addArmy(defenceBattleField.getReserve().toArmy());
//	                    if (!BattleFieldUI.isGetOff) {
	                    	JOptionPane.showMessageDialog(null, "Enemy "+IsLandUI.house[houseID].getName()+" won");
	                    	// đóng cửa sổ giao diện đánh nhau
	                    	Window[] windows = Window.getWindows();
                            for (Window w: windows) {
                            	if (w instanceof JDialog) w.dispose();
                            }
//	                    }
//	                    else BattleFieldUI.isGetOff = true;
	                } else if (defenceBattleField.isAllDead()) {
	                	
	                	// khi thắng
	                	
	                	
//	                	System.out.println("defence");
	                    war = false;
	                    
	                    // thời gian về nhà  = 2 lần thời gian đi của các quân (bị thương, mệt mỏi)
	                    int timebackhome = (int) (attackBattleField.getReserve().toArmy().getSpeedTimeForWholeRoute()*1.0/Integer.parseInt((String)IsLandUI.cbbTimeGo.getSelectedItem()));
	                    
	                    Timer timer = new Timer();
	                    timer.schedule(new TimerTask() {
	                        @Override
	                        public void run() {
	                            IsLandUI.myHouse.getArmy().addArmy(attackBattleField.getReserve().toArmy());

	                            
//	                            // cho cái you won vào trong để hiện cùng với cái came back home
//	                            JOptionPane.showMessageDialog(null, "You won "+IsLandUI.house[houseID].getName());
	                            JOptionPane.showMessageDialog(null, "Units came back home");
	                            
	                            // về nhà xong thì đóng cái mẹ kia lại (giao diện waveattack)
	                            Window[] windows = Window.getWindows();
	                            for (Window w: windows) {
	                            	if (w instanceof JDialog) w.dispose();
	                            }
	                        }
	                    }, timebackhome); 
	                    // thời gian quay về sau khi thắng
	                    
	                    // cái này sẽ hiện ra trước cái kia à. khi mà nó hiện came back hôm tức là quân đã về nhà mẹ rồi
	                    JOptionPane.showMessageDialog(null, "You won "+IsLandUI.house[houseID].getName());
	                 
	                    // sau khi thắng thì phải đóng giao diện đánh nhau
	                    IsLandUI.bffUI.dispose();
//	                    System.out.println("đóng");
	                    
	                    // đặt thời gian quay về vào trong bộ đếm ngược
	                    sentRealArmy.forEach((key, arrList) -> {
	                        for (int j = 0; j < arrList.size(); j++) {
	                            SentArmy sentArmy = arrList.get(arrList.size() - j - 1);
	                            sentArmy.setArrivalTime(System.currentTimeMillis() + timebackhome);
	                            sentArmy.isdel = true;
//	                            System.out.println(sentArmy.getArrivalTime());
	                        }
	                    });
	                    
	                    // đánh xong thì xóa cái quân được gửi đi
	                    IsLandUI.myHouse.getSentRealArmy().get(houseID).clear();
	                    
	                } else {
	                    boolean isAllWall = defenceBattleField.isAllWall();
	                    attackBattleField.addToField(isAllWall);
	                    defenceBattleField.addToField(isAllWall);
	                    startAttack();
	                }
	                
	
	            }
//            }
        }, (long) (15*60000*1.0/(Integer.parseInt((String)IsLandUI.cbbTimeRound.getSelectedItem()))));
        // Integer.parseInt((String)IsLandUI.cbbTimeRound.getSelectedItem())*
        // thời gian mỗi hiệp
    }
                

    public BattleField getAttackBattleField() {
        return attackBattleField;
    }

    public void setAttackBattleField(BattleField attackBattleField) {
        this.attackBattleField = attackBattleField;
    }

    public BattleField getDefenceBattleField() {
        return defenceBattleField;
    }

    public void setDefenceBattleField(BattleField defenceBattleField) {
        this.defenceBattleField = defenceBattleField;
    }

    public boolean isWar() {
        return war;
    }

    public void setWar(boolean war) {
        this.war = war;
    }

}
