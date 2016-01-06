package lib.dalcoms.andengineheesanglib.buttons;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.MoveXModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.util.GLState;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.color.Color;

public class IconTextButtonSprite extends Sprite {
	ITextureRegion regionIcon;
	VertexBufferObjectManager myVbom;
	Sprite spriteIcon;
	String stringPrompt = "";
	boolean isIconRotate = false;
	final float paddingRatio = 0.05f;
	boolean flagTouchAction = false;
	Font textFont;

	public IconTextButtonSprite(float pX, float pY,
			ITextureRegion pTextureRegion, VertexBufferObjectManager vbom,
			ITextureRegion iconRegion, boolean pIsIconRotate, String sPrompt,
			Color pColor, Font pFont, Color pTextColor) {

		super(pX, pY, pTextureRegion, vbom);

		regionIcon = iconRegion;
		stringPrompt = sPrompt;
		myVbom = vbom;
		isIconRotate = pIsIconRotate;
		textFont = pFont;

		if(pColor!=null){
			changeColor(pColor);
		}
		
		putTextOn(sPrompt, pTextColor, vbom,
				regionIcon.getWidth() + this.getWidth() * paddingRatio / 2);
		attachIcon();
	}

	private void putTextOn(String str, Color myColor,
			VertexBufferObjectManager vbom, float xPos) {
		final float textMaxHeight = this.getHeight() - 2 * this.getHeight()
				* paddingRatio;
		final float textMaxWidth = this.getWidth()
				- (2 * this.getWidth() * paddingRatio + xPos);

		float textHeight = 1f;
		float textWidth = 1f;
		float textDownScale = 1f;
		float hRatio = 1f;
		float wRatio = 1f;

		Text strText = new Text(0, 0, textFont, str, new TextOptions(
				HorizontalAlign.LEFT), vbom) {
		};

		textHeight = strText.getHeight();
		textWidth = strText.getWidth();

		hRatio = textMaxHeight / textHeight;
		wRatio = textMaxWidth / textWidth;

		if ((textHeight > textMaxHeight) && (textWidth > textMaxWidth)) {
			if (hRatio > wRatio) {
				textDownScale = wRatio;
			} else {
				textDownScale = hRatio;
			}
		} else if ((textHeight > textMaxHeight) && (textWidth < textMaxWidth)) {
			textDownScale = hRatio;
		} else if ((textHeight < textMaxHeight) && (textWidth > textMaxWidth)) {
			textDownScale = wRatio;
		}
		strText.setScale(textDownScale);
		strText.setColor(myColor);

		strText.setPosition(xPos, (this.getHeight() - strText.getHeight()) / 2);

		attachChild(strText);

	}

	private void attachIcon() {
		spriteIcon = new Sprite(0, 0, regionIcon, myVbom) {
			@Override
			protected void preDraw(final GLState pGLState, final Camera pCamera) {
				super.preDraw(pGLState, pCamera);
				pGLState.enableDither();
			}
		};
		attachChild(spriteIcon);
		if (isIconRotate == true) {
			spriteIcon.registerEntityModifier(new LoopEntityModifier(
					new RotationModifier(5f, 0, 360)));
		}
	}

	public void changeColor(Color pColor) {
		this.setColor(pColor);
	}

	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if (pSceneTouchEvent.isActionDown()) {// slide icon from left to right.
			spriteIcon.registerEntityModifier(new MoveXModifier(0.8f, 0, this
					.getWidth() - spriteIcon.getWidth()) {
				@Override
				protected void onModifierFinished(IEntity pItem) {
					// TODO : Action as touchEvent.isActionUp()
					super.onModifierFinished(pItem);
					buttonActionEvent();
				}
			});
			isActionDownEvent();
		} else {
			if (pSceneTouchEvent.isActionUp()) {
				// TODO : CLICK SOUND PLAY
				// TODO : ACTION.......
				// ResourcesManager.getInstance().soundBtnClick.play();
				buttonActionEvent();
			}
		}
		return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX,
				pTouchAreaLocalY);
	}

	public void isActionDownEvent() {
		// TODO Auto-generated method stub
		
	}

	public String getPromptText() {
		return stringPrompt;
	}

	private void buttonActionEvent() {
		if (flagTouchAction == false) {
			flagTouchAction = true;
			doButtonAction();
		}
	}

	public void doButtonAction() {
//		TODO : 
	}
}
