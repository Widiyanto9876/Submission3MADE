package com.widiyanto.kamusbahasa.Database;

import android.provider.BaseColumns;

public class DatabaseContractEI {
    static String TABLE_EI = "table_kamus_ei";
    static final class KamusColumns implements BaseColumns {

        // Kamus Text
        static String TEXT_EI = "text";
        // Kamus Detail
        static String DETAIL_EI = "detail";
    }
}
