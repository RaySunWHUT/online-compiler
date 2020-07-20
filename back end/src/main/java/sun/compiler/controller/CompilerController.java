package sun.compiler.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.compiler.service.CompilerService;

import java.io.IOException;

/**
 * author: Sun Rui
 */
@CrossOrigin
@RestController
@RequestMapping(value = {"/compile"}, method = RequestMethod.POST)
public class CompilerController {

    private static Logger logger = Logger.getLogger(CompilerController.class);

    @Autowired
    CompilerService compilerService;

    @RequestMapping(value = {"/pythonInterface"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject compile(@RequestBody JSONObject pythonFile) throws IOException {

        return compilerService.compile(pythonFile);

    }

}
