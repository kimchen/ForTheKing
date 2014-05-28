package com.smik.fortheking;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.http.util.VersionInfo;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends Activity {
	private final int SLOT_MAX_NUM = 25;
	private final int COL_NUM = 5;
	
	private List<Slot> btns = new ArrayList<Slot>();
	private TextView scoreText = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			
			scoreText = (TextView)rootView.findViewById(R.id.ScoreText);
			scoreText.setText("Score : ");
			btns.add((Slot)rootView.findViewById(R.id.Button01));
			btns.add((Slot)rootView.findViewById(R.id.Button02));
			btns.add((Slot)rootView.findViewById(R.id.Button03));
			btns.add((Slot)rootView.findViewById(R.id.Button04));
			btns.add((Slot)rootView.findViewById(R.id.Button05));
			btns.add((Slot)rootView.findViewById(R.id.Button06));
			btns.add((Slot)rootView.findViewById(R.id.Button07));
			btns.add((Slot)rootView.findViewById(R.id.Button08));
			btns.add((Slot)rootView.findViewById(R.id.Button09));
			btns.add((Slot)rootView.findViewById(R.id.Button10));
			btns.add((Slot)rootView.findViewById(R.id.Button11));
			btns.add((Slot)rootView.findViewById(R.id.Button12));
			btns.add((Slot)rootView.findViewById(R.id.Button13));
			btns.add((Slot)rootView.findViewById(R.id.Button14));
			btns.add((Slot)rootView.findViewById(R.id.Button15));
			btns.add((Slot)rootView.findViewById(R.id.Button16));
			btns.add((Slot)rootView.findViewById(R.id.Button17));
			btns.add((Slot)rootView.findViewById(R.id.Button18));
			btns.add((Slot)rootView.findViewById(R.id.Button19));
			btns.add((Slot)rootView.findViewById(R.id.Button20));
			btns.add((Slot)rootView.findViewById(R.id.Button21));
			btns.add((Slot)rootView.findViewById(R.id.Button22));
			btns.add((Slot)rootView.findViewById(R.id.Button23));
			btns.add((Slot)rootView.findViewById(R.id.Button24));
			btns.add((Slot)rootView.findViewById(R.id.Button25));
			
			spawnSlot();
			spawnSlot();
			spawnSlot();
			spawnSlot();
			spawnSlot();
			redraw();
			return rootView;
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_LEFT){
			boolean result = false;
			for(int i = 0;i < SLOT_MAX_NUM;i++){
				int nextIndex = checkDirIndex(i,keyCode);
				if(nextIndex < 0)
					continue;
				if(checkCanCombine(btns.get(i),btns.get(nextIndex)))
					result = true;
			}
			if(result){
				redraw();
				checkKing();
				spawnSlot();
				redraw();
			}
			return true;
		}else if(keyCode == KeyEvent.KEYCODE_DPAD_DOWN || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT){
			boolean result = false;
			for(int i = SLOT_MAX_NUM - 1;i >= 0;i--){
				int nextIndex = checkDirIndex(i,keyCode);
				if(nextIndex < 0)
					continue;
				if(checkCanCombine(btns.get(i),btns.get(nextIndex)))
					result = true;
			}
			if(result){
				redraw();
				checkKing();
				spawnSlot();
				redraw();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	private void checkKing(){
		for(Slot slot : btns){
			if(slot.isEmpty)
				continue;
			if(slot.number == Slot.MAX_NUMER){
				scoreText.setText(scoreText.getText() + "\n" + slot.getText());
				slot.isEmpty = true;
			}
		}
	}
	
	private void spawnSlot(){
		int ranIndex = (int)(Math.random() * SLOT_MAX_NUM);
		Slot slot = btns.get(ranIndex);
		if(!slot.isEmpty){
			spawnSlot();
			return;
		}
		int ranType = (int)(Math.random() * 4);
		if(ranType == 0)
			slot.type = SlotType.SPADES;
		else if(ranType == 1)
			slot.type = SlotType.HEARTS;
		else if(ranType == 2)
			slot.type = SlotType.DIAMONDS;
		else if(ranType == 3)
			slot.type = SlotType.CLUBS;
		
		int ranNum = (int)(Math.random() * 3) + 1;
		slot.number = ranNum;
		slot.isEmpty = false;
	}
	
	private void redraw(){
		for(Slot slot : btns){
			if(slot.isEmpty)
				slot.setText(" ");
			else{
				String text = "";
				if(slot.type == SlotType.SPADES)
					text += "¶Â®ç";
				else if(slot.type == SlotType.HEARTS)
					text += "¬õ¤ß";
				else if(slot.type == SlotType.DIAMONDS)
					text += "Æp¥Û";
				else if(slot.type == SlotType.CLUBS)
					text += "±öªá";
				
				if(slot.number <=10)
					text += slot.number;
				else if(slot.number == 11)
					text += "J";
				else if(slot.number == 12)
					text += "Q";
				else if(slot.number == 13)
					text += "K";
				
				slot.setText(text);
			}
		}
	}
	
	private boolean checkCanCombine(Slot ori, Slot target){
		if(ori.isEmpty)
			return false;
		if(target.isEmpty){
			target.type = ori.type;
			target.number = ori.number;
			target.isEmpty = false;
			ori.isEmpty = true;
			return true;
		}
		if(ori.type != target.type)
			return false;
		int newNum = ori.number + target.number;
		if(newNum > Slot.MAX_NUMER)
			return false;
		target.number = newNum;
		ori.isEmpty = true;
		return true;
	}
	
	private int checkDirIndex(int index,int keyCode){
		int newIndex = -1;
		if(keyCode == KeyEvent.KEYCODE_DPAD_UP){
			newIndex = index - COL_NUM;
		}else if(keyCode == KeyEvent.KEYCODE_DPAD_DOWN){
			newIndex = index + COL_NUM;
		}else if(keyCode == KeyEvent.KEYCODE_DPAD_LEFT){
			if(index % COL_NUM != 0)
				newIndex = index - 1;
		}else if(keyCode == KeyEvent.KEYCODE_DPAD_RIGHT){
			if(index % COL_NUM != COL_NUM - 1)
				newIndex = index + 1;
		}
		if(newIndex < 0 || newIndex >= SLOT_MAX_NUM)
			return -1;
		return newIndex;
	}
}
