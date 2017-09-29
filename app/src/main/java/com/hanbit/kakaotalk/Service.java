package com.hanbit.kakaotalk;

import java.util.ArrayList;


/**
 * Created by 1027 on 2017-09-29.
 */

public class Service {
    public static interface iPredicate{
        public void execute();
    }
    public static interface iPost{
        public void execute(Object o);
    }
    public static interface iList{
        public ArrayList<?> execute(Object o);
    }
    public static interface iGet{
        public Object execute(Object o);
    }
    public static interface iUpdate{
        public void execute(Object o);
    }
    public static interface iDelete{
        public void execute(Object o);
    }
}
