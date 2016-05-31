package models;

/**
 * Created by grishberg on 31.05.16.
 */
public class Model implements Comparable<Model> {
    String id_problem;
    String id_abon;
    String id_subject;
    String date_create;
    String date_last;
    String status;
    String close_date;
    String close_user;
    String user_create;
    String close_type;
    String e_date;
    String e_user;
    String estimation;
    String id_group;
    String id_cause;
    String abon_comment;
    String id_priority;
    String last_id_task;
    String last_id_sms;
    String id_client_pers;
    String performer;
    String id_problem_parent;
    String id_call_reason;
    String id_group_create;

    public Model(String line[]) {
        int i = 0;
        this.id_problem = line[i++];
        this.id_abon = line[i++];
        this.id_subject = line[i++];
        this.date_create = line[i++];
        this.date_last = line[i++];
        this.status = line[i++];
        this.close_date = line[i++];
        this.close_user = line[i++];
        this.user_create = line[i++];
        this.close_type = line[i++];
        this.e_date = line[i++];
        this.e_user = line[i++];
        this.estimation = line[i++];
        this.id_group = line[i++];
        this.id_cause = line[i++];
        this.abon_comment = line[i++];
        this.id_priority = line[i++];
        this.last_id_task = line[i++];
        this.last_id_sms = line[i++];
        this.id_client_pers = line[i++];
        this.performer = line[i++];
        this.id_problem_parent = line[i++];
        this.id_call_reason = line[i++];
        this.id_group_create = line[i++];
    }

    public String[] getLine() {
        return new String[]{id_problem
                , id_abon
                , id_subject
                , date_create
                , date_last
                , status
                , close_date
                , close_user
                , user_create
                , close_type
                , e_date
                , e_user
                , estimation
                , id_group
                , id_cause
                , abon_comment
                , id_priority
                , last_id_task
                , last_id_sms
                , id_client_pers
                , performer
                , id_problem_parent
                , id_call_reason
                , id_group_create};
    }

    public int compareTo(Model o) {
        if (o == null || o.id_abon == null) {
            return -1;
        }
        if (this.id_abon == null) {
            return 1;
        }
        return this.id_abon.compareTo(o.id_abon);
    }

    public String getId_problem() {
        return id_problem;
    }

    public String getId_abon() {
        return id_abon;
    }

    public String getId_subject() {
        return id_subject;
    }

    public String getDate_create() {
        return date_create;
    }

    public String getDate_last() {
        return date_last;
    }

    public String getStatus() {
        return status;
    }

    public String getClose_date() {
        return close_date;
    }

    public String getClose_user() {
        return close_user;
    }

    public String getUser_create() {
        return user_create;
    }

    public String getClose_type() {
        return close_type;
    }

    public String getE_date() {
        return e_date;
    }

    public String getE_user() {
        return e_user;
    }

    public String getEstimation() {
        return estimation;
    }

    public String getId_group() {
        return id_group;
    }

    public String getId_cause() {
        return id_cause;
    }

    public String getAbon_comment() {
        return abon_comment;
    }

    public String getId_priority() {
        return id_priority;
    }

    public String getLast_id_task() {
        return last_id_task;
    }

    public String getLast_id_sms() {
        return last_id_sms;
    }

    public String getId_client_pers() {
        return id_client_pers;
    }

    public String getPerformer() {
        return performer;
    }

    public String getId_problem_parent() {
        return id_problem_parent;
    }

    public String getId_call_reason() {
        return id_call_reason;
    }

    public String getId_group_create() {
        return id_group_create;
    }
}
