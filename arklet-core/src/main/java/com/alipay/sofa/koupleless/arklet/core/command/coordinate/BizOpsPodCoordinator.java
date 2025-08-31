/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alipay.sofa.koupleless.arklet.core.command.coordinate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.alipay.sofa.ark.common.util.StringUtils;

/**
 * <p>
 * BizOpsPodCoordinator class.
 * </p>
 *
 * @author liuzhuoheng
 * @since 2025/7/15
 * @version 1.0.0
 */
public class BizOpsPodCoordinator {

    /**
     * bizIdentityLockMap
     * key: bizIdentity, value: bizModelVersion
     */
    private static final Map<String, String> bizIdentityLockMap = new ConcurrentHashMap<>();

    /**
     * <p>
     * save.
     * </p>
     * 
     * @param bizIdentity     a {@link java.lang.String} object
     * @param bizModelVersion a {@link java.lang.String} object
     * @return
     */
    public static void save(String bizIdentity, String bizModelVersion) {
        if (StringUtils.isEmpty(bizModelVersion)) {
            bizModelVersion = StringUtils.EMPTY_STRING;
        }
        bizIdentityLockMap.put(bizIdentity, bizModelVersion);
    }

    /**
     * <p>
     * remove.
     * </p>
     *
     * @param bizIdentity     a {@link java.lang.String} object
     * @param bizModelVersion a {@link java.lang.String} object
     * @return
     */
    public static void remove(String bizIdentity, String bizModelVersion) {
        bizIdentityLockMap.remove(bizIdentity, bizModelVersion);
    }

    /**
     * <p>
     * canAccess.
     * </p>
     * 
     * @param bizIdentity     a {@link java.lang.String} object
     * @param bizModelVersion a {@link java.lang.String} object
     * @return a boolean
     */
    public static boolean canAccess(String bizIdentity, String bizModelVersion) {
        return StringUtils.isEmpty(bizModelVersion)
                || StringUtils.isEmpty(bizIdentityLockMap.get(bizIdentity))
                || bizIdentityLockMap.get(bizIdentity).equals(bizModelVersion);
    }

}
