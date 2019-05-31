/*
 * Copyright 2018 Google LLC. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pnoker.rtmp.handle;

import com.pnoker.rtmp.bean.Global;
import com.pnoker.rtmp.bean.Task;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * <p>Copyright(c) 2018. Pnoker All Rights Reserved.
 * <p>Author     : Pnoker
 * <p>Email      : pnokers@gmail.com
 * <p>Description:
 */
@Slf4j
public class TaskHandel implements Runnable {
    @Override
    public void run() {
        log.info("Rtsp->Rtmp任务队列监听线程已启动");
        try {
            while (true) {
                Task task = Global.taskQueue.take();
                log.info("starting task {} , command {}", task.getTaskId(), task.getCommand());
                task.start();
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}