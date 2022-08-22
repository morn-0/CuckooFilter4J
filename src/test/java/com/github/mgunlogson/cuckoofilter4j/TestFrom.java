package com.github.mgunlogson.cuckoofilter4j;

import java.nio.charset.Charset;

import org.junit.Test;

import com.google.common.hash.Funnels;

/**
 * @author lizhaofa
 * @date 2022-08-23 00:34
 */
public class TestFrom {

    @Test
    public void testCreateFrom() {
        String[] strs = new String[10000];
        for (int i = 0; i < 10000; i++) {
            strs[i] = i + "_test";
        }

        CuckooFilter<CharSequence> a = new CuckooFilter.Builder<>(Funnels.stringFunnel(Charset.defaultCharset()), 200000).build();
        for (String str : strs) {
            a.put(str);
        }
        for (String str : strs) {
            System.out.println(a.mightContain(str));
        }

        CuckooFilter<CharSequence> b = new CuckooFilter.Builder<>(Funnels.stringFunnel(Charset.defaultCharset()), 200000).build();
        b.bits(a.bits());
        b.config(a.config());

        b.delete(strs[strs.length - 2]);
        b.delete(strs[strs.length - 1]);
        for (String str : strs) {
            System.out.println(b.mightContain(str));
        }
    }

}
