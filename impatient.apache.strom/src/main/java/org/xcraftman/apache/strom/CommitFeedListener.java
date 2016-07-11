package org.xcraftman.apache.strom;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by xianguanghuang on 2016/3/19. For Note and Demo purpose
 */
public class CommitFeedListener extends BaseRichSpout {

    private SpoutOutputCollector outputCollector;
    private List<String> commits;
    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("commit"));
    }

    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {

        this.outputCollector = spoutOutputCollector;
        try {
            InputStream systemResourceAsStream = ClassLoader.getSystemResourceAsStream("changelog.txt");
            commits = IOUtils.readLines(
                    systemResourceAsStream, "UTF-8"

            );
            System.out.println();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void nextTuple() {
        for (String commit : commits){
            outputCollector.emit(new Values(commit));
        }
    }
}
