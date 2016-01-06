package lib.dalcoms.andengineheesanglib.buttons;

import java.util.ArrayList;

import lib.dalcoms.andengineheesanglib.utils.HsMath;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.color.Color;

import android.util.Log;

public class LevelViewButton extends Sprite {

	private final String TAG = this.getClass().getSimpleName();

	private int mLevel, mAchievement, mAchieveMax;
	ArrayList<TiledSprite> mAchievementSprites = new ArrayList<TiledSprite>();
	AnimatedSprite mLevelBgSprite;
	private VertexBufferObjectManager vbom;

	private String mLevelName = "";
	private int mIntTag = 0;
	private boolean mEnable = false;
	private boolean mSelected = false;
	private boolean mAnimateOnSelected = true;

	private Font mTextFont;
	private Color mLevelEnabledTextColor;
	private Color mLevelDisabledTextColor;
	private Text levelNameText;

	private final long ANIMATE_DURATION = 300;

	public LevelViewButton( float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, int pLevel,
			Font pLevelTextFont, Color pLevelEnabledTextColor,
			Color pLevelDisabledTextColor, ITiledTextureRegion pLevelRegion,
			int pAchievement, int pAchieveMax,
			ITiledTextureRegion pAchievementRegion ) {

		super( pX, pY, pTextureRegion, pVertexBufferObjectManager );

		setArgs( pVertexBufferObjectManager, pLevel, pLevelTextFont,
				pLevelEnabledTextColor, pLevelDisabledTextColor, pLevelRegion,
				pAchievement, pAchieveMax, pAchievementRegion );

		attachSprites( pLevelRegion, pAchievement, pAchieveMax,
				pAchievementRegion );
	}

	private void attachSprites( ITiledTextureRegion pLevelRegion,
			int pAchievement, int pAchieveMax,
			ITiledTextureRegion pAchievementRegion ) {

		attachAchievementSprites( pAchievementRegion );
		attachLevelBgSprite( pLevelRegion );
	}

	private void attachLevelBgSprite( ITiledTextureRegion pLevelRegion ) {
		mLevelBgSprite = new AnimatedSprite( 0f, 0f, pLevelRegion, vbom );
		attachChild( mLevelBgSprite );
	}

	private void attachAchievementSprites( ITiledTextureRegion pAchievementRegion ) {
		createAchievementSprites( pAchievementRegion );
		for ( TiledSprite pSprite : mAchievementSprites ) {
			attachChild( pSprite );
		}
		setAchievement( mAchievement );
	}

	private void setAchievement( int pAchievement ) {
		for ( int i = 0 ; i < mAchievementSprites.size() ; i++ ) {
			if ( i < mAchievement ) {
				mAchievementSprites.get( i ).setCurrentTileIndex( 1 );
			} else {
				mAchievementSprites.get( i ).setCurrentTileIndex( 0 );
			}
		}
	}

	private void createAchievementSprites( ITiledTextureRegion pAchievementRegion ) {
		HsMath hsMath = new HsMath();
		final float[] pX = hsMath.getDistributedCenterOrgPosition(
				pAchievementRegion.getWidth(), getAchievementMax(),
				this.getWidth() - this.getHeight() - this.getHeight() * 0.5f,
				this.getHeight() + this.getHeight() * 0.25f );
		final float pY = ( this.getHeight() - pAchievementRegion.getHeight() ) * 0.5f;

		for ( int i = 0 ; i < getAchievementMax() ; i++ ) {
			mAchievementSprites.add( new TiledSprite( pX[i], pY,
					pAchievementRegion, vbom ) );
		}
	}

	private void setArgs( VertexBufferObjectManager pVertexBufferObjectManager,
			int pLevel, Font pLevelTextFont, Color pLevelEnabledTextColor,
			Color pLevelDisabledTextColor, ITiledTextureRegion pLevelRegion,
			int pAchievement, int pAchieveMax,
			ITiledTextureRegion pAchievementRegion ) {

		this.vbom = pVertexBufferObjectManager;
		this.mLevel = pLevel;
		this.mAchievement = pAchievement;
		this.mAchieveMax = pAchieveMax;
		this.mTextFont = pLevelTextFont;

		this.mLevelEnabledTextColor = pLevelEnabledTextColor;
		this.mLevelDisabledTextColor = pLevelDisabledTextColor;
	}

	public void setAchievementLevel( int pAchievement ) {
		if ( pAchievement > this.mAchieveMax ) {
			Log.e( TAG, "achievement value is over max" );
			this.mAchievement = mAchieveMax;
		} else {
			this.mAchievement = pAchievement;
		}
		this.setAchievement( mAchievement );
	}

	public int getAchievementLevel( ) {
		return this.mAchievement;
	}

	public int getAchievementMax( ) {
		return this.mAchieveMax;
	}

	@Override
	public boolean onAreaTouched( TouchEvent pSceneTouchEvent,
			float pTouchAreaLocalX, float pTouchAreaLocalY ) {
		if ( pSceneTouchEvent.isActionUp() && isEnable() ) {
			onButtonClick( getIntTag() );
		}
		return super.onAreaTouched( pSceneTouchEvent, pTouchAreaLocalX,
				pTouchAreaLocalY );
	}

	public void onButtonClick( int pIntTag ) {

	}

	public LevelViewButton setIntTag( int pIntTag ) {
		this.mIntTag = pIntTag;

		return this;
	}

	public int getIntTag( ) {
		return this.mIntTag;
	}

	public void enable( boolean pEnable ) {
		mEnable = pEnable;
		setLevelTextColor( getLevelTextColor( pEnable ) );
	}

	public boolean isEnable( ) {
		return mEnable;
	}

	public String getLevelName( ) {
		return this.mLevelName;
	}

	public void setLevelName( String pLevelName ) {
		this.mLevelName = pLevelName;
		putLevelNameText( pLevelName );
	}

	private void putLevelNameText( String pLevelName ) {
		final float paddingRatio = 0.05f;

		final float textMaxHeight = this.getHeight() - 2 * this.getHeight()
				* paddingRatio;
		final float textMaxWidth = textMaxHeight;

		float textHeight = 1f;
		float textWidth = 1f;
		float textDownScale = 1f;
		float hRatio = 1f;
		float wRatio = 1f;

		levelNameText = new Text( 0, 0, this.mTextFont, pLevelName,
				new TextOptions( HorizontalAlign.LEFT ), vbom );

		textHeight = levelNameText.getHeight();
		textWidth = levelNameText.getWidth();

		hRatio = textMaxHeight / textHeight;
		wRatio = textMaxWidth / textWidth;

		if ( ( textHeight > textMaxHeight ) && ( textWidth > textMaxWidth ) ) {
			if ( hRatio > wRatio ) {
				textDownScale = wRatio;
			} else {
				textDownScale = hRatio;
			}
		} else if ( ( textHeight > textMaxHeight ) && ( textWidth < textMaxWidth ) ) {
			textDownScale = hRatio;
		} else if ( ( textHeight < textMaxHeight ) && ( textWidth > textMaxWidth ) ) {
			textDownScale = wRatio;
		}
		levelNameText.setScale( textDownScale );

		levelNameText.setColor( getLevelTextColor( this.isEnable() ) );

		levelNameText.setPosition(
				( this.getHeight() - levelNameText.getWidth() ) * 0.5f,
				( this.getHeight() - levelNameText.getHeight() ) * 0.5f );

		attachChild( levelNameText );
	}

	private Color getLevelTextColor( boolean pIsEnable ) {
		Color ret = this.mLevelDisabledTextColor;
		if ( pIsEnable ) {
			ret = this.mLevelEnabledTextColor;
		}
		return ret;
	}

	private void setLevelTextColor( Color pColor ) {
		this.levelNameText.setColor( pColor );
	}

	public boolean isSelected( ) {
		return mSelected;
	}

	public void select( boolean pSelect ) {
		this.mSelected = pSelect;
		if ( isAnimtedOnSelected() ) {
			animateBgOnSelect( pSelect );
		}
	}

	private void animateBgOnSelect( boolean pSelect ) {
		if ( pSelect == true ) {
			mLevelBgSprite.animate( ANIMATE_DURATION );
		} else {
			mLevelBgSprite.stopAnimation( 0 );
		}
	}

	public boolean isAnimtedOnSelected( ) {
		return mAnimateOnSelected;
	}

	public LevelViewButton setAnimatedOnSelected( boolean pAnimatedOnSelect ) {
		this.mAnimateOnSelected = pAnimatedOnSelect;

		return this;
	}

}
