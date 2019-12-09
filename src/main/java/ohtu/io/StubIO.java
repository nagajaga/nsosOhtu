package ohtu.io;

import java.util.ArrayList;

public class StubIO implements IO {

    String[] inputs;
    int index;
    public ArrayList<String> outputs;

    public StubIO(String... inputs) {
        this.inputs = inputs;
        this.outputs = new ArrayList<>();
        this.index = 0;
    }

    @Override
    public void print(String s) {
        outputs.add(s);
    }

    @Override
    public String nextLine() {
        return inputs[index++];
    }

    @Override
    public void println(String s) {
        outputs.add(s);
    }
}
