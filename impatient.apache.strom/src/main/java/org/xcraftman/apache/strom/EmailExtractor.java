package org.xcraftman.apache.strom;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

/**
 * Created by xianguanghuang on 2016/3/19. For Note and Demo purpose
 */
public class EmailExtractor extends BaseBasicBolt {
    @Override
    public void execute(Tuple tuple, BasicOutputCollector basicOutputCollector) {
        String commit = tuple.getStringByField("commit");
        String[] parts = commit.split(" ");
        basicOutputCollector.emit(new Values(parts[1]));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("email"));
    }
}
