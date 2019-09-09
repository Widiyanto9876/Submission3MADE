package com.widiyanto.kamusbahasa.Database;

import android.provider.BaseColumns;

public class DatabaseContractIE {
    static String TABLE_IE = "table_kamus_ie";
    static final class KamusColumns implements BaseColumns {

        // Kamus Text
        static String TEXT_IE = "text";
        // Kamus Detail
        static String DETAIL_IE = "detail";
    }
}