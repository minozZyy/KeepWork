package com.keepwork.fileetl.model;


public class FileETL {
    public String groupid;
    public String topicname;
    public String partitionname;
    public String useoffset;
    public String statusts;

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getTopicname() {
        return topicname;
    }

    public void setTopicname(String topicname) {
        this.topicname = topicname;
    }

    public String getPartitionname() {
        return partitionname;
    }

    public void setPartitionname(String partitionname) {
        this.partitionname = partitionname;
    }

    public String getUseoffset() {
        return useoffset;
    }

    public void setUseoffset(String useoffset) {
        this.useoffset = useoffset;
    }

    public String getStatusts() {
        return statusts;
    }

    public void setStatusts(String statusts) {
        this.statusts = statusts;
    }
}
