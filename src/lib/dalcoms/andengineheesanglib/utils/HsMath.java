package lib.dalcoms.andengineheesanglib.utils;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Point;
import android.graphics.PointF;

public class HsMath {

	public float getRandomRangeFloat( float pMin, float pMax ) {
		float rv = 0f;
		Random rand = new Random();

		rv = ( pMax - pMin ) * rand.nextFloat() + pMin;

		return rv;
	}

	public int getRandomRangeInt( int pMin, int pMax ) {
		int rv = 0;
		Random rand = new Random();

		rv = rand.nextInt( ( pMax - pMin ) + 1 ) + pMin;

		return rv;
	}

	public long getRandomRangeLong( long pMin, long pMax ) {
		long rv = 0;
		Random rand = new Random();

		rv = ( pMax - pMin ) * rand.nextLong() + pMin;

		return rv;
	}

	public float[] getDistributedCenterOrgPosition( float objectLength, int objectNum,
			float relatedObjectLength, float offset ) {

		float[] retOrgPosition = new float[objectNum];

		for ( int i = 0 ; i < objectNum ; i++ ) {
			retOrgPosition[i] = offset + ( ( relatedObjectLength - objectLength ) / ( objectNum - 1 ) )
					* ( float ) i;
		}

		return retOrgPosition;
	}

	public ArrayList<PointF> getDistributedOrgCoordinate( PointF pObjectArea, Point pObjectArrayNum,
			PointF pArrayArea, PointF pOffset, boolean pOrderVertical ) {
		ArrayList<PointF> result = new ArrayList<PointF>();

		float[] arrayX = this.getDistributedCenterOrgPosition( pObjectArea.x, pObjectArrayNum.x,
				pArrayArea.x, pOffset.x );
		float[] arrayY = this.getDistributedCenterOrgPosition( pObjectArea.y, pObjectArrayNum.y,
				pArrayArea.y, pOffset.y );

		if ( pOrderVertical == false ) {
			for ( float y : arrayY ) {
				for ( float x : arrayX ) {
					result.add( new PointF( x, y ) );
				}
			}
		} else {// Order horizontal
			for ( float x : arrayX ) {
				for ( float y : arrayY ) {
					result.add( new PointF( x, y ) );
				}
			}
		}

		return result;
	}

	public int getAlignCenterInt( int in_, int base_ ) {
		return ( base_ - in_ ) >> 1;
	}

	public float getAlignCenterFloat( float in_, float base_ ) {
		return ( base_ - in_ ) / 2;
	}

	public float getAlignBottomYFloat( float in_h, float base_y, float base_h ) {
		return base_y + base_h - in_h;
	}

}
