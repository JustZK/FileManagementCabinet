package com.zk.cabinet.db;

import com.zk.cabinet.dao.DaoSession;
import com.zk.common.utils.LogUtil;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;
import org.greenrobot.greendao.rx.RxDao;

import java.util.List;

public abstract class BaseService<T, K> {

    public static final String TAG = "BaseService";
    public static final String CHECK_INIT = "Please check for the initialization";

    private DaoSession daoSession;
    private AbstractDao<T, K> dao;

    public void init(DaoSession daoSession, AbstractDao<T, K> dao) {
        this.daoSession = daoSession;
        this.dao = dao;
    }

    public synchronized void insert(T item) {
        if (dao == null) {
            LogUtil.Companion.getInstance().d(TAG, CHECK_INIT, false);
        } else {
            dao.insert(item);
        }
    }

    public synchronized void insert(T... items) {
        if (dao == null) {
            LogUtil.Companion.getInstance().d(TAG, CHECK_INIT, false);
        } else {
            dao.insertInTx(items);
        }
    }

    public synchronized void insert(List<T> items) {
        if (dao == null) {
            LogUtil.Companion.getInstance().d(TAG, CHECK_INIT, false);
        } else {
            dao.insertInTx(items);
        }
    }

    public synchronized void insertOrReplace(T item) {
        if (dao == null) {
            LogUtil.Companion.getInstance().d(TAG, CHECK_INIT, false);
        } else {
            dao.insertOrReplace(item);
        }
    }

    public synchronized void insertOrReplace(T... items) {
        if (dao == null) {
            LogUtil.Companion.getInstance().d(TAG, CHECK_INIT, false);
        } else {
            dao.insertOrReplaceInTx(items);
        }
    }

    public synchronized void insertOrReplace(List<T> items) {
        if (dao == null) {
            LogUtil.Companion.getInstance().d(TAG, CHECK_INIT, false);
        } else {
            dao.insertOrReplaceInTx(items);
        }
    }

    public synchronized void insertOrIgnore(final Iterable<T> entities) {
        if (dao == null || daoSession == null) {
            LogUtil.Companion.getInstance().d(TAG, CHECK_INIT, false);
        } else {
            daoSession.runInTx(new Runnable() {
                @Override
                public void run() {
                    for (T entity : entities) {
                        try {
                            dao.insert(entity);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }


    public synchronized void insertOrIgnore(final T... entities) {
        if (dao == null || daoSession == null) LogUtil.Companion.getInstance().d(TAG, CHECK_INIT, false);
        else {
            daoSession.runInTx(new Runnable() {
                @Override
                public void run() {
                    for (T entity : entities) {
                        try {
                            dao.insert(entity);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

    public synchronized void deleteByKey(K key) {
        if (dao == null) LogUtil.Companion.getInstance().d(TAG, CHECK_INIT, false);
        else dao.deleteByKey(key);
    }

    public synchronized void delete(T item) {
        if (item == null) return;
        if (dao == null) LogUtil.Companion.getInstance().d(TAG, CHECK_INIT, false);
        else dao.delete(item);
    }

    public synchronized void delete(T... items) {
        if (items == null) return;
        if (dao == null) LogUtil.Companion.getInstance().d(TAG, CHECK_INIT, false);
        else dao.deleteInTx(items);
    }

    public synchronized void delete(List<T> items) {
        if (items == null) return;
        if (dao == null) LogUtil.Companion.getInstance().d(TAG, CHECK_INIT, false);
        else dao.deleteInTx(items);
    }

    public synchronized void deleteAll() {
        if (dao == null) LogUtil.Companion.getInstance().d(TAG, CHECK_INIT, false);
        else dao.deleteAll();
    }

    public synchronized void update(T item) {
        if (dao == null) LogUtil.Companion.getInstance().d(TAG, CHECK_INIT, false);
        else dao.update(item);
    }

    public synchronized void update(T... items) {
        if (dao == null) LogUtil.Companion.getInstance().d(TAG, CHECK_INIT, false);
        else dao.updateInTx(items);
    }

    public synchronized void update(List<T> items) {
        if (dao == null) LogUtil.Companion.getInstance().d(TAG, CHECK_INIT, false);
        else dao.updateInTx(items);
    }

    public T load(K key) {
        if (dao == null) {
            LogUtil.Companion.getInstance().d(TAG, CHECK_INIT, false);
            return null;
        }
        return dao.load(key);
    }

    public List<T> loadAll() {
        if (dao == null) {
            LogUtil.Companion.getInstance().d(TAG, CHECK_INIT, false);
            return null;
        }
        return dao.loadAll();
    }

    public List<T> query(WhereCondition cond1, WhereCondition... condMore) {
        if (dao == null) {
            LogUtil.Companion.getInstance().d(TAG, CHECK_INIT, false);
            return null;
        }
        return dao.queryBuilder().where(cond1, condMore).build().list();
    }

    public List<T> queryRaw(String where, String... params) {
        if (dao == null) {
            LogUtil.Companion.getInstance().d(TAG, CHECK_INIT, false);
            return null;
        }
        return dao.queryRaw(where, params);
    }

    public QueryBuilder<T> queryBuilder() {
        if (dao == null) {
            LogUtil.Companion.getInstance().d(TAG, CHECK_INIT, false);
            return null;
        }
        return dao.queryBuilder();
    }

    public long count() {
        if (dao == null) {
            LogUtil.Companion.getInstance().d(TAG, CHECK_INIT, false);
            return 0;
        }
        return dao.count();
    }

    public void refresh(T item) {
        if (dao == null) LogUtil.Companion.getInstance().d(TAG, CHECK_INIT, false);
        else dao.refresh(item);
    }

    public void detach(T item) {
        if (dao == null) LogUtil.Companion.getInstance().d(TAG, CHECK_INIT, false);
        else dao.detach(item);
    }

    public AbstractDao<T, K> getDao() {
        return dao;
    }

    public RxDao<T, K> getRxDao() {
        if (dao == null) {
            LogUtil.Companion.getInstance().d(TAG, CHECK_INIT, false);
            return null;
        }
        return dao.rx();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public QueryBuilder<T> getQueryBuilder() {
        if (dao == null) {
            LogUtil.Companion.getInstance().d(TAG, CHECK_INIT, false);
            return null;
        }
        return dao.queryBuilder();
    }



}
