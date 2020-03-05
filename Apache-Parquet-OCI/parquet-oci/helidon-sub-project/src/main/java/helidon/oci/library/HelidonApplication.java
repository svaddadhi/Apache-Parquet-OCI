package helidon.oci.library;

/*
 * Copyright (c) 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.security.UnresolvedPermission;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

//import io.helidon.common.CollectionsHelper;

/**
 *
 */
@ApplicationScoped
@ApplicationPath("/")
public class HelidonApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> set = new HashSet<>();
        set.add(UploadResource.class);
        set.add(DownloadResource.class);
        set.add(GreetResource.class);
        return Collections.unmodifiableSet(set);
    }

//    @Override
//    public Set<Class<?>> getClasses() {
//        return Set.of(GreetResource.class);
//    }
}
