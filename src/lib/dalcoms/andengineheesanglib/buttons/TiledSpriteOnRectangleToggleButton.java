package lib.dalcoms.andengineheesanglib.buttons;

/*
 * --------
 * |   ()   |
 * --------
 * tiledSpite on Rectangle 
 * touch event : shake and toggle action....
 */
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;

public class TiledSpriteOnRectangleToggleButton extends Rectangle {

	ITiledTextureRegion mITiledRegionToggleButton;
	boolean mButtonStatus;
	Rectangle mTouchEffectRect;
	VertexBufferObjectManager vbom;
	TiledSprite mToggleIconSprite;

	public TiledSpriteOnRectangleToggleButton( float pX, float pY, float pWidth, float pHeight,
			VertexBufferObjectManager pVertexBufferObjectManager,
			ITiledTextureRegion pITiledRegionToggleButton,
			boolean pButtonStatus ) {

		super( pX, pY, pWidth, pHeight, pVertexBufferObjectManager );

		vbom = pVertexBufferObjectManager;
		mITiledRegionToggleButton = pITiledRegionToggleButton;
		mButtonStatus = pButtonStatus;
		setColor( 0f, 0f, 0f, 0f );

		attachTiledSpriteOnCenter( pButtonStatus );
	}

	private void attachTiledSpriteOnCenter( boolean pButtonStatus ) {
		attachChild( createTouchEffect() );
		attachChild( createToggleTiledSprite( pButtonStatus ) );
	}

	private TiledSprite createToggleTiledSprite( boolean pButtonStatus ) {
		final float pX = ( this.getWidth() - mITiledRegionToggleButton.getWidth() ) / 2f;
		final float pY = ( this.getHeight() - mITiledRegionToggleButton.getHeight() ) / 2f;
		mToggleIconSprite = new TiledSprite( pX, pY, mITiledRegionToggleButton, vbom );
		mToggleIconSprite.setCurrentTileIndex( pButtonStatus ? 1 : 0 );
		//		attachChild( mToggleIconSprite );
		return ( mToggleIconSprite );
	}

	private Rectangle createTouchEffect( ) {
		final float pWidth = getWidth() * 0.8f;
		final float pHeight = getHeight();
		final float pY = 0;
		final float pX = ( getWidth() - pWidth ) / 2f;
		mTouchEffectRect = new Rectangle( pX, pY, pWidth, pHeight, vbom );
		//		mTouchEffectRect.setColor( 1f, 1f, 1f, 0.35f );
		mTouchEffectRect.setVisible( false );
		//		attachChild( mTouchEffectRect );
		return ( mTouchEffectRect );
	}

	public void setEffectColor( Color pColor ) {
		mTouchEffectRect.setColor( pColor );
	}

	public float getCenterX( ) {
		return this.getX() + this.getWidth() / 2f;
	}

	public float getCenterY( ) {
		return this.getY() + this.getHeight() / 2f;
	}

	public boolean getButtonStatus( ) {
		return this.mButtonStatus;
	}

	private void togleButtonStatus( ) {
		mButtonStatus = !mButtonStatus;
	}

	@Override
	public boolean onAreaTouched( TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY ) {
		if ( pSceneTouchEvent.isActionDown() ) {
			mTouchEffectRect.setVisible( true );
			mTouchEffectRect.registerEntityModifier( new ScaleModifier( 0.2f, 0.2f, 1f ) {
				@Override
				protected void onModifierFinished( IEntity pItem ) {
					super.onModifierFinished( pItem );
					mTouchEffectRect.setVisible( false );
				}
			} );
		} else {
			if ( pSceneTouchEvent.isActionUp() ) {
				mTouchEffectRect.setVisible( false );
				onButtonToggled();
			}
		}

		return super.onAreaTouched( pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY );
	}

	public void onButtonToggled( ) {
		togleButtonStatus();
		mToggleIconSprite.setCurrentTileIndex( getButtonStatus() ? 1 : 0 );
	}

}
