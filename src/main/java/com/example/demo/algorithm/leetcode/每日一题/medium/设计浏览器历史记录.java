package com.example.demo.algorithm.leetcode.每日一题.medium;

public class 设计浏览器历史记录 {
    public static void main(String[] args) {
        BrowserHistory browserHistory = new BrowserHistory("leetcode.com");
        browserHistory.visit("google.com");
        browserHistory.visit("google.com");
        browserHistory.visit("google.com");
        browserHistory.back(1);
        browserHistory.back(1);
        browserHistory.forward(1);
        browserHistory.visit("linkedin.com");
        browserHistory.forward(2);
        browserHistory.back(2);
        browserHistory.back(7);
    }
}


class BrowserHistory {

    BrowseNode bn;

    public BrowserHistory(String homepage) {
        bn = new BrowseNode(homepage);
    }

    public void visit(String url) {
        bn.next = new BrowseNode(url);
        bn.next.prev = bn;
        bn = bn.next;
    }

    public String back(int steps) {
        String res = bn.url;
        while (bn.prev != null && steps-- > 0) {
            bn = bn.prev;
            res = bn.url;
        }
        return res;
    }

    public String forward(int steps) {
        String res = bn.url;
        while (bn.next != null && steps-- > 0) {
            bn = bn.next;
            res = bn.url;
        }
        return res;
    }
}

class BrowseNode {
    String url;
    BrowseNode next;
    BrowseNode prev;
    public BrowseNode(String url) {
        this.url = url;
    }
}
/**
 * Your BrowserHistory object will be instantiated and called as such:
 * BrowserHistory obj = new BrowserHistory(homepage);
 * obj.visit(url);
 * String param_2 = obj.back(steps);
 * String param_3 = obj.forward(steps);
 */
