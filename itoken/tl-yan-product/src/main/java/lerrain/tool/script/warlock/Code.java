package lerrain.tool.script.warlock;

import lerrain.tool.formula.Factors;
import lerrain.tool.formula.Formula;

public interface Code extends Formula
{
	public Object run(Factors factors);
	
	public String toText(String space);

	//public void printCode();
}
