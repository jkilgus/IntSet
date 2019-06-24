/**
 * IntSet.java
 * Copyright 2018, Craig A. Damon
 * all rights reserved
 */
package edu.vtc.cis2260;

/**
 * IntSet - set of integers, no repeats, like {1,4,8}
 * @author Craig A. Damon
 *
 */

public class IntSet
{
	/**
	 * initialize the empty set
	 */
	public IntSet()
	{
		_valuesInUse = 0;
		_values = new int[10];
		repOK();
	}
	
	/**
	 * initialize the set containing only n
	 * @param n the lone initial value in the set
	 */
	public IntSet(int n)
	{
		_valuesInUse = 1;
		_values = new int[10];
		_values[0] = n;
		repOK();
	}
	
	/**
	 * copy the set orig into this set
	 * @param orig
	 */
	public IntSet(IntSet orig)
	{
		_valuesInUse = orig.size();
		_values = new int[_valuesInUse+10];
		for (int i = 0; i < _valuesInUse; i++)
			_values[i] = orig._values[i];
		repOK();
	}
	
	/**
	 * gee the number of values in this set
	 * @return the size, >= 0
	 */
	public int size()
	{
		return _valuesInUse;
	}
	/**
	 * does this set contain n
	 * @param n the value in question
	 * @return true iff this contains n
	 */
	public boolean contains(int n)
	{
		for (int i = 0; i < _valuesInUse; i++)
			{
				if (_values[i] == n)
					return true;
			}
		return false;
	}
	
	/**
	 * add n to this set if n is not already there, does nothing if n already present
	 * MODIFIES: n is guaranteed to be in this set after this call
	 * @param n the value being inserted
	 */
	public void insert(int n)
	{
		for (int i = 0; i < _valuesInUse; i++)
			{
				if (_values[i] == n)
					return; // already there
			}
		if (_valuesInUse == _values.length)
			{
				int[] oldValues = _values;
				_values = new int[_valuesInUse+20];
				for (int i = 0; i < _valuesInUse; i++)
					_values[i] = oldValues[i];
			}
		_values[_valuesInUse] = n;
		_valuesInUse++;
		repOK();
	}
	
	/**
	 * remove n from this set if n is already there, does nothing if not there
	 * MODIFIES: n is guaranteed not to be in this set after this call
	 * @param n the value being removed
	 */
	public void remove(int n)
	{
		for (int i = 0; i < _valuesInUse; i++)
			{
				if (_values[i] == n)
					{
						for (int j = i+1; j < _valuesInUse; j++)
							_values[j-1] = _values[j];
						_valuesInUse--;
						_values[_valuesInUse] = 0;
						repOK();
						return;
					}
			}
	}
	
	/**
	 * @return the elements of the set (in no guaranteed order) inside {}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		boolean first = true;
		for (int i = 0; i < _valuesInUse; i++)
			{
				if (first)
					first = false;
				else
					sb.append(",");
				sb.append(" ");
				sb.append(_values[i]);
			}
		sb.append(" }");
		return new String(sb);
	}
	
	private void repOK()
	{
		assert(_valuesInUse >= 0);
		assert(_values != null);
		assert(_valuesInUse <= _values.length);
		for (int i = 0; i < _valuesInUse-1; i++)
			{
				for (int j = i+1; j < _valuesInUse; j++)
					{
						assert(_values[i] != _values[j]): "duplicate value "+_values[i]+" at "+i+" and "+j;
					}
			}
		for (int i = _valuesInUse; i < _values.length; i++)
			assert(_values[i] == 0);
	}
	
	// rep
	private int _valuesInUse;  // always >= 0, <= _values.length
	private int[] _values;  // never null, the first _valuesInUse elements define the set, everything else is 0
	                        // no duplicates in the first _valuesInUse elements

	/** description
	 * @param args
	 */
	public static void main(String[] args)
	{
		IntSet is = new IntSet();
		System.out.println(is+" contains 1 is "+is.contains(1));
		is.insert(2);
		System.out.println(is+" contains 1 is "+is.contains(1));
		is.insert(1);
		System.out.println(is+" contains 1 is "+is.contains(1));
		is = new IntSet(1);
		System.out.println(is+" contains 1 is "+is.contains(1));
		IntSet is2 = new IntSet(is);
		System.out.println("copied "+is+" into "+is2);
	}

}
