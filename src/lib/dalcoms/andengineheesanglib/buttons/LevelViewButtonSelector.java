package lib.dalcoms.andengineheesanglib.buttons;

import java.util.ArrayList;

import org.andengine.entity.modifier.IEntityModifier;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;

import android.graphics.PointF;


/*UnKnown Error occurred!!
 * Give up...
 */
public class LevelViewButtonSelector {
	Scene mScene;

	ArrayList<LevelViewButton> mButtonSprites = new ArrayList<LevelViewButton>();
	
	public LevelViewButtonSelector(){
	}

	public LevelViewButtonSelector makeButtons(int pButtonCount,
			ITextureRegion pButtonTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,
			Font pLevelTextFont, Color pLevelEnabledTextColor,
			Color pLevelDisabledTextColor, ITiledTextureRegion pLevelRegion,
			int pAchievement, int pAchieveMax,
			ITiledTextureRegion pAchievementRegion) {

		int pLevelIndex = 0;

		for (int i = 0; i < pButtonCount; i++) {
			pLevelIndex = i;

			mButtonSprites.add(new LevelViewButton(0f, 0f,
					pButtonTextureRegion, pVertexBufferObjectManager,
					pLevelIndex, pLevelTextFont, pLevelEnabledTextColor,
					pLevelDisabledTextColor, pLevelRegion, pAchievement,
					pAchieveMax, pAchievementRegion) {
				@Override
				public void onButtonClick(int pIntTag) {
					selectOnClickButton(this.getIntTag());
					onButtonClick(this.getIntTag());
				}
			}.setIntTag(i));
		}

		return this;
	}

	public LevelViewButtonSelector makeButtons(int pButtonCount,
			ITextureRegion pButtonTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,
			Font pLevelTextFont, Color pLevelEnabledTextColor,
			Color pLevelDisabledTextColor, ITiledTextureRegion pLevelRegion,
			int pAchievement, int pAchieveMax,
			ITiledTextureRegion pAchievementRegion, ArrayList<PointF> pArrayPos) {

		makeButtons(pButtonCount, pButtonTextureRegion,
				pVertexBufferObjectManager, pLevelTextFont,
				pLevelEnabledTextColor, pLevelDisabledTextColor, pLevelRegion,
				pAchievement, pAchieveMax, pAchievementRegion);

		return setPosition(pArrayPos);
	}

	public LevelViewButtonSelector setPosition(ArrayList<PointF> pArrayPos) {
		for (int i = 0; i < pArrayPos.size(); i++) {
			mButtonSprites.get(i).setPosition(pArrayPos.get(i).x,
					pArrayPos.get(i).y);
		}
		return this;
	}

	public ArrayList<LevelViewButton> getButtons() {
		return mButtonSprites;
	}

	public LevelViewButton getButton(int pObjectIntTag) {
		return mButtonSprites.get(pObjectIntTag);
	}

	// have to override
	public void onButtonClick(int pObjectIntTag) {

	}

	private void selectOnClickButton(int pObjectIntTag) {
		for (LevelViewButton pButton : mButtonSprites) {
			if (pButton.getIntTag() == pObjectIntTag) {
				pButton.enable(true);
			} else {
				pButton.enable(false);
			}
		}
	}

	public LevelViewButtonSelector attachButtonsToScene(Scene pScene,
			boolean pTouchEn, IEntityModifier pEntityModifier) {
		for (LevelViewButton btn : getButtons()) {
			pScene.attachChild(btn);

			if (pTouchEn == true) {
				pScene.registerTouchArea(btn);
			}
			btn.registerEntityModifier(pEntityModifier);
		}

		return this;
	}

	public LevelViewButtonSelector attachButtonsToScene(Scene pScene,
			boolean pTouchEn) {
		for (LevelViewButton btn : getButtons()) {
			pScene.attachChild(btn);

			if (pTouchEn == true) {
				pScene.registerTouchArea(btn);
			}
		}

		return this;
	}
}
