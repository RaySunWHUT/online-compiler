package sun.compiler.service;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

public interface CompilerService {

    JSONObject compile(JSONObject pythonCode) throws IOException;

}
