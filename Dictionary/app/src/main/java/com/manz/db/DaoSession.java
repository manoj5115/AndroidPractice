package com.manz.db;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.manz.db.Words;

import com.manz.db.WordsDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig wordsDaoConfig;

    private final WordsDao wordsDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        wordsDaoConfig = daoConfigMap.get(WordsDao.class).clone();
        wordsDaoConfig.initIdentityScope(type);

        wordsDao = new WordsDao(wordsDaoConfig, this);

        registerDao(Words.class, wordsDao);
    }
    
    public void clear() {
        wordsDaoConfig.getIdentityScope().clear();
    }

    public WordsDao getWordsDao() {
        return wordsDao;
    }

}