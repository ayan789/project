package lerrain.tool.script.warlock.statement;

import lerrain.tool.formula.Factors;
import lerrain.tool.script.ScriptRuntimeException;
import lerrain.tool.script.warlock.Code;
import lerrain.tool.script.warlock.CodeImpl;
import lerrain.tool.script.warlock.analyse.Expression;
import lerrain.tool.script.warlock.analyse.Words;

import java.math.BigDecimal;

public class ArithmeticSub extends CodeImpl
{
	Code lc, rc;

	public ArithmeticSub(Words ws, int i)
	{
		super(ws, i);

		if (i > 0)
			lc = Expression.expressionOf(ws.cut(0, i));
		else
			lc = null;

		rc = Expression.expressionOf(ws.cut(i + 1));
	}

	public Object run(Factors factors)
	{
		Object l = lc == null ? 0 : lc.run(factors);
		Object r = rc.run(factors);

		if (l instanceof BigDecimal && r instanceof BigDecimal)
		{
			return ((BigDecimal) l).subtract((BigDecimal) r);
		}

		throw new ScriptRuntimeException(this, factors, "只可以对数字做减法运算：" + l + " - " + r);
	}

	public String toText(String space)
	{
		return lc == null ? "-" + rc.toText("") : (lc.toText("") + " - " + rc.toText(""));
	}
}
