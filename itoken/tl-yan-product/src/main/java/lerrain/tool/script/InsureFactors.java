package lerrain.tool.script;

import com.neo.InsureFactorChecker;

import lombok.Data;

@Data
public class InsureFactors extends Stack {
	InsureFactorChecker insureFactorChecker = new InsureFactorChecker();

	@Override
	public Object get(String name) {
		Stack stack = new Stack();
		stack.set("aaa", "aaa");
		try {
			if ("UNDERWRITING_DECISION".equals(name)) {
				return insureFactorChecker.checkUnderwritingDecision();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.get(name);
	}
}
