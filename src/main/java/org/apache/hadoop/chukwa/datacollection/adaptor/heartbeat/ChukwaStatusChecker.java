/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hadoop.chukwa.datacollection.adaptor.heartbeat;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.hadoop.chukwa.datacollection.adaptor.AdaptorException;
import org.apache.hadoop.chukwa.datacollection.agent.ChukwaAgent;
import org.json.simple.JSONObject;

public class ChukwaStatusChecker implements StatusChecker {
  JSONObject status = new JSONObject();
  ChukwaAgent agent;
  
  @SuppressWarnings("unchecked")
  public ChukwaStatusChecker() throws AdaptorException{
    agent = ChukwaAgent.getAgent();
    status.put("component", "Chukwa.Agent");
    try {
      status.put("host", InetAddress.getLocalHost().getHostName());
    } catch (UnknownHostException e) {
      throw new AdaptorException("Could not get localhost name", e.getCause());
    }
  }
  
  @SuppressWarnings("unchecked")
  @Override
  public JSONObject getStatus() {
    status.put("adaptor.count", agent.getAdaptorList().size());
    return status;
  }

  @Override
  public void init(String... args) {
    //not used    
  }

}
