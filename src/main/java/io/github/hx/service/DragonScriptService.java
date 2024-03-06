package io.github.hx.service;

import org.redisson.api.RScript;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class DragonScriptService {

    @Resource
    RedissonClient redissonClient;

    public List<Object> executeLuaScript(String round,int damage) throws IOException {

        RScript script = redissonClient.getScript();
        String luaScript = new String(Files.readAllBytes(Paths.get("doc/dragon.lua")));


        // BUG 原因是序列化的问题
        List<Object> result = script.eval(RScript.Mode.READ_WRITE, luaScript, RScript.ReturnType.MULTI,
                Collections.singletonList(round), Integer.valueOf(damage));

        return result;
    }
}