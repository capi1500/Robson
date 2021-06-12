import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import robson.Robson;
import robson.exceptions.BladWykonania;
import robson.exceptions.NieprawidlowyProgram;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScriptTests{
	private static class TestConfig{
		public static class FunctionCall{
			private final String name;
			private final Object expectedValue;
			private final Number[] args;
			
			public String getName(){
				return name;
			}
			
			public Object getExpectedValue(){
				return expectedValue;
			}
			
			public Number[] getArgs(){
				return args;
			}
			
			public FunctionCall(String name, Object expectedValue, Number... args){
				this.name = name;
				this.expectedValue = expectedValue;
				this.args = args;
			}
		}
		
		private final String name;
		private final String path;
		private Object expectedValue;
		private FunctionCall[] functionCalls;
		
		public String getName(){
			return name;
		}
		
		public String getPath(){
			return path;
		}
		
		public Object getExpectedValue(){
			return expectedValue;
		}
		
		public FunctionCall[] getFunctionCalls(){
			return functionCalls;
		}
		
		public TestConfig(String name){
			this.path = "src/main/resources/" + name + ".json";
			this.name = name;
		}
		
		public TestConfig(String name, Object expectedValue){
			this(name);
			this.expectedValue = expectedValue;
		}
		
		public TestConfig(String name, FunctionCall... functionCalls){
			this(name);
			this.functionCalls = functionCalls;
		}
	}
	
	private final Robson script = new Robson();
	private final TestConfig[] executionTestConfigs = new TestConfig[]{
			new TestConfig("value", 1.0),
			new TestConfig("block", -6.0),
			new TestConfig("ifTrue", 1.0),
			new TestConfig("ifFalse", 0.0),
			new TestConfig("not", 1.0),
			new TestConfig("and", 3.0),
			new TestConfig("or", 1.0),
			new TestConfig("less", 2.0),
			new TestConfig("greater", 2.0),
			new TestConfig("lessEqual", 1.0),
			new TestConfig("greaterEqual", 1.0),
			new TestConfig("equals", 5.0),
			new TestConfig("addition", 15.0),
			new TestConfig("subtraction", -1.0),
			new TestConfig("multiplication", 56.0),
			new TestConfig("division", 7.0 / 8.0),
			new TestConfig("assignment", 321.0),
			new TestConfig("variable", 205.0),
			new TestConfig("while", 10.0),
			
			new TestConfig("example1", 15.0),
			new TestConfig("example2", 8.0),
			
			new TestConfig("functionCall", 25.0),
			new TestConfig("functionCallAng", 25.0),
			new TestConfig("access", 3.14),
			new TestConfig("tableAccess", 4.0),
			new TestConfig("modulo", 3.0)
	};
	private final TestConfig[] functionCallTestConfigs = new TestConfig[]{
			new TestConfig("fib",
					new TestConfig.FunctionCall(
							"Fibonacci", 5.0, 5
					),
					new TestConfig.FunctionCall(
							"Fibonacci", 21.0, 8
					),
					new TestConfig.FunctionCall(
							"Fibonacci", 2.0, 3
					),
					new TestConfig.FunctionCall(
							"Fibonacci", 5.0, 5
					),
					new TestConfig.FunctionCall(
							"Fibonacci", 21.0, 8
					),
					new TestConfig.FunctionCall(
							"Fibonacci", 2.0, 3
					)
			),
		    new TestConfig("table",
					new TestConfig.FunctionCall(
							"get", 1.0, 0
					),
					new TestConfig.FunctionCall(
							"get", 2.0, 1
					),
					new TestConfig.FunctionCall(
							"get", 3.0, 2
					),
					new TestConfig.FunctionCall(
							"get", 4.0, 3
					),
					new TestConfig.FunctionCall(
							"get", 5.0, 4
					)
			)
	};
	
	void executionTest(TestConfig config) throws NieprawidlowyProgram, BladWykonania{
		script.fromJson(config.getPath());
		assertEquals(config.getExpectedValue(), script.wykonaj());
	}
	
	void functionTest(TestConfig config) throws NieprawidlowyProgram, BladWykonania{
		script.fromJson(config.getPath());
		for(TestConfig.FunctionCall functionCall : config.getFunctionCalls()){
			assertEquals(
					functionCall.getExpectedValue(), 
					script.wykonaj(functionCall.getName(), functionCall.getArgs())
			);
		}
	}
	
	@TestFactory
	Stream<DynamicTest> execution(){
		return Arrays.stream(executionTestConfigs).map(
				testConfig -> DynamicTest.dynamicTest(
						testConfig.getName(), 
						() -> executionTest(testConfig)
				)
		);
	}
	
	@TestFactory
	Stream<DynamicTest> functionCall(){
		return Arrays.stream(functionCallTestConfigs).map(
				testConfig -> DynamicTest.dynamicTest(
						testConfig.getName(),
						() -> functionTest(testConfig)
				)
		);
	}
}
