package sun.compiler.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.compiler.constants.Constants;
import sun.compiler.service.CompilerService;
import sun.compiler.util.CompilerUtil;
import sun.compiler.util.FileUtil;
import sun.compiler.util.JSONUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service("compilerService")
@Transactional
public class CompilerServiceImpl implements CompilerService {

    @Override
    public JSONObject compile(JSONObject pythonFile) throws IOException {

        String code = pythonFile.getString("code");

        String directory = Constants.DATA_LOCATION;

        String fileName = FileUtil.getStringID();

        String suffix = Constants.SUFFIX;

        String absolutePath = directory + fileName + suffix;

        File file = new File(absolutePath);

        if (!file.exists()) {    // 若文件不存在, 则创建文件

            file.createNewFile();

            FileWriter fw = new FileWriter(absolutePath);
            BufferedWriter bfr = new BufferedWriter(fw);

            bfr.write(code);
            bfr.newLine();
            bfr.close();

        }

        String compile_result = CompilerUtil.compile(absolutePath);

        JSONObject jsonObject = new JSONObject();

        if (compile_result == null) {

            return JSONUtil.errorJSON(Constants.GLOBAL_ERROR);

        } else {

            jsonObject.put("response", compile_result);

            return JSONUtil.successJSON(jsonObject);
        }

    }

}
