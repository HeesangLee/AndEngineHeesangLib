package lib.dalcoms.data;

import java.util.ArrayList;

public class GameLevel {
	private final String TAG = this.getClass().getSimpleName();
	private int mLevelCount;
	private int mAchievementMax;
	private ArrayList<String> mLevelNames = new ArrayList<String>();
	private ArrayList<Integer> mAchievements = new ArrayList<Integer>();
	private ArrayList<Boolean> mActivations = new ArrayList<Boolean>();
	
	private int mSelectedLevel = 0;

	public GameLevel() {

	}

	public GameLevel(int pLevelCount) {
		this.mLevelCount = pLevelCount;
	}

	public void setLevelCount(int pLevelCount) {
		this.mLevelCount = pLevelCount;
	}

	public ArrayList<String> getLevelNames() {
		return this.mLevelNames;
	}

	public boolean setLevelNames(ArrayList<String> pLevelNames) {
		boolean ret = false;
		if (pLevelNames.size() == mLevelCount) {
			ret = true;
			mLevelNames.clear();
			mLevelNames.addAll(pLevelNames);
		}

		return ret;
	}

	public String getLevelName(int pIndex) {
		return mLevelNames.get(pIndex);
	}

	public GameLevel setAchievementMax(int pAchievementMax) {
		this.mAchievementMax = pAchievementMax;
		return this;
	}

	public int getAchievementMax() {
		return this.mAchievementMax;
	}

	public String getTag() {
		return this.TAG;
	}

	public int getLevelCount() {
		return this.mLevelCount;
	}

	public boolean setAchievements(ArrayList<Integer> pAchievements) {
		boolean ret = false;
		if (pAchievements.size() == mLevelCount) {
			ret = true;
			mAchievements.clear();
			mAchievements.addAll(pAchievements);
		}
		return ret;
	}

	public ArrayList<Integer> getAchievements() {
		return this.mAchievements;
	}

	public void setAchievement(int pIndex, Integer pAchievement) {
		this.mAchievements.set(pIndex, pAchievement);
	}

	public Integer getAchievement(int pIndex) {
		return this.mAchievements.get(pIndex);
	}

	public boolean setActivation(ArrayList<Boolean> pActivations) {
		boolean ret = false;

		if (pActivations.size() == mLevelCount) {
			ret = true;
			mActivations.clear();
			mActivations.addAll(pActivations);
		}

		return ret;
	}

	public ArrayList<Boolean> getActivations() {
		return this.mActivations;
	}

	public Boolean getActivation(int pIndex) {
		return this.mActivations.get(pIndex);
	}

	public int getLastActivatedLevelIntex() {
		int ret = 0;
		for (int index = 0; index < this.mActivations.size(); index++) {
			if (mActivations.get(index) == true) {
				ret = index;
			}
		}
		return ret;
	}
	
	public int getSelectedLevel(){
		return this.mSelectedLevel;
	}
	public void setSelectedLevel(int pSelectedLevel){
		this.mSelectedLevel = pSelectedLevel;
	}
}
