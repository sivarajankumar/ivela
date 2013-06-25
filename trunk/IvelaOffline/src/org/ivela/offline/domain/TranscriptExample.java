package org.ivela.offline.domain;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TranscriptExample {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table transcript
     *
     * @ibatorgenerated Thu Apr 15 14:20:41 BRT 2010
     */
    protected String orderByClause;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table transcript
     *
     * @ibatorgenerated Thu Apr 15 14:20:41 BRT 2010
     */
    protected List oredCriteria;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table transcript
     *
     * @ibatorgenerated Thu Apr 15 14:20:41 BRT 2010
     */
    public TranscriptExample() {
        oredCriteria = new ArrayList();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table transcript
     *
     * @ibatorgenerated Thu Apr 15 14:20:41 BRT 2010
     */
    protected TranscriptExample(TranscriptExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table transcript
     *
     * @ibatorgenerated Thu Apr 15 14:20:41 BRT 2010
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table transcript
     *
     * @ibatorgenerated Thu Apr 15 14:20:41 BRT 2010
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table transcript
     *
     * @ibatorgenerated Thu Apr 15 14:20:41 BRT 2010
     */
    public List getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table transcript
     *
     * @ibatorgenerated Thu Apr 15 14:20:41 BRT 2010
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table transcript
     *
     * @ibatorgenerated Thu Apr 15 14:20:41 BRT 2010
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table transcript
     *
     * @ibatorgenerated Thu Apr 15 14:20:41 BRT 2010
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table transcript
     *
     * @ibatorgenerated Thu Apr 15 14:20:41 BRT 2010
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table transcript
     *
     * @ibatorgenerated Thu Apr 15 14:20:41 BRT 2010
     */
    public static class Criteria {
        protected List criteriaWithoutValue;

        protected List criteriaWithSingleValue;

        protected List criteriaWithListValue;

        protected List criteriaWithBetweenValue;

        protected Criteria() {
            super();
            criteriaWithoutValue = new ArrayList();
            criteriaWithSingleValue = new ArrayList();
            criteriaWithListValue = new ArrayList();
            criteriaWithBetweenValue = new ArrayList();
        }

        public boolean isValid() {
            return criteriaWithoutValue.size() > 0
                || criteriaWithSingleValue.size() > 0
                || criteriaWithListValue.size() > 0
                || criteriaWithBetweenValue.size() > 0;
        }

        public List getCriteriaWithoutValue() {
            return criteriaWithoutValue;
        }

        public List getCriteriaWithSingleValue() {
            return criteriaWithSingleValue;
        }

        public List getCriteriaWithListValue() {
            return criteriaWithListValue;
        }

        public List getCriteriaWithBetweenValue() {
            return criteriaWithBetweenValue;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteriaWithoutValue.add(condition);
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            Map map = new HashMap();
            map.put("condition", condition);
            map.put("value", value);
            criteriaWithSingleValue.add(map);
        }

        protected void addCriterion(String condition, List values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            Map map = new HashMap();
            map.put("condition", condition);
            map.put("values", values);
            criteriaWithListValue.add(map);
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            List list = new ArrayList();
            list.add(value1);
            list.add(value2);
            Map map = new HashMap();
            map.put("condition", condition);
            map.put("values", list);
            criteriaWithBetweenValue.add(map);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return this;
        }

        public Criteria andIdIn(List values) {
            addCriterion("id in", values, "id");
            return this;
        }

        public Criteria andIdNotIn(List values) {
            addCriterion("id not in", values, "id");
            return this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return this;
        }

        public Criteria andGradeIsNull() {
            addCriterion("grade is null");
            return this;
        }

        public Criteria andGradeIsNotNull() {
            addCriterion("grade is not null");
            return this;
        }

        public Criteria andGradeEqualTo(Long value) {
            addCriterion("grade =", value, "grade");
            return this;
        }

        public Criteria andGradeNotEqualTo(Long value) {
            addCriterion("grade <>", value, "grade");
            return this;
        }

        public Criteria andGradeGreaterThan(Long value) {
            addCriterion("grade >", value, "grade");
            return this;
        }

        public Criteria andGradeGreaterThanOrEqualTo(Long value) {
            addCriterion("grade >=", value, "grade");
            return this;
        }

        public Criteria andGradeLessThan(Long value) {
            addCriterion("grade <", value, "grade");
            return this;
        }

        public Criteria andGradeLessThanOrEqualTo(Long value) {
            addCriterion("grade <=", value, "grade");
            return this;
        }

        public Criteria andGradeIn(List values) {
            addCriterion("grade in", values, "grade");
            return this;
        }

        public Criteria andGradeNotIn(List values) {
            addCriterion("grade not in", values, "grade");
            return this;
        }

        public Criteria andGradeBetween(Long value1, Long value2) {
            addCriterion("grade between", value1, value2, "grade");
            return this;
        }

        public Criteria andGradeNotBetween(Long value1, Long value2) {
            addCriterion("grade not between", value1, value2, "grade");
            return this;
        }

        public Criteria andSystemUserIsNull() {
            addCriterion("system_user is null");
            return this;
        }

        public Criteria andSystemUserIsNotNull() {
            addCriterion("system_user is not null");
            return this;
        }

        public Criteria andSystemUserEqualTo(Long value) {
            addCriterion("system_user =", value, "systemUser");
            return this;
        }

        public Criteria andSystemUserNotEqualTo(Long value) {
            addCriterion("system_user <>", value, "systemUser");
            return this;
        }

        public Criteria andSystemUserGreaterThan(Long value) {
            addCriterion("system_user >", value, "systemUser");
            return this;
        }

        public Criteria andSystemUserGreaterThanOrEqualTo(Long value) {
            addCriterion("system_user >=", value, "systemUser");
            return this;
        }

        public Criteria andSystemUserLessThan(Long value) {
            addCriterion("system_user <", value, "systemUser");
            return this;
        }

        public Criteria andSystemUserLessThanOrEqualTo(Long value) {
            addCriterion("system_user <=", value, "systemUser");
            return this;
        }

        public Criteria andSystemUserIn(List values) {
            addCriterion("system_user in", values, "systemUser");
            return this;
        }

        public Criteria andSystemUserNotIn(List values) {
            addCriterion("system_user not in", values, "systemUser");
            return this;
        }

        public Criteria andSystemUserBetween(Long value1, Long value2) {
            addCriterion("system_user between", value1, value2, "systemUser");
            return this;
        }

        public Criteria andSystemUserNotBetween(Long value1, Long value2) {
            addCriterion("system_user not between", value1, value2, "systemUser");
            return this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return this;
        }

        public Criteria andStatusIn(List values) {
            addCriterion("status in", values, "status");
            return this;
        }

        public Criteria andStatusNotIn(List values) {
            addCriterion("status not in", values, "status");
            return this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return this;
        }

        public Criteria andScoreIsNull() {
            addCriterion("score is null");
            return this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("score is not null");
            return this;
        }

        public Criteria andScoreEqualTo(BigDecimal value) {
            addCriterion("score =", value, "score");
            return this;
        }

        public Criteria andScoreNotEqualTo(BigDecimal value) {
            addCriterion("score <>", value, "score");
            return this;
        }

        public Criteria andScoreGreaterThan(BigDecimal value) {
            addCriterion("score >", value, "score");
            return this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("score >=", value, "score");
            return this;
        }

        public Criteria andScoreLessThan(BigDecimal value) {
            addCriterion("score <", value, "score");
            return this;
        }

        public Criteria andScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("score <=", value, "score");
            return this;
        }

        public Criteria andScoreIn(List values) {
            addCriterion("score in", values, "score");
            return this;
        }

        public Criteria andScoreNotIn(List values) {
            addCriterion("score not in", values, "score");
            return this;
        }

        public Criteria andScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("score between", value1, value2, "score");
            return this;
        }

        public Criteria andScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("score not between", value1, value2, "score");
            return this;
        }

        public Criteria andAverageExerciseIsNull() {
            addCriterion("average_exercise is null");
            return this;
        }

        public Criteria andAverageExerciseIsNotNull() {
            addCriterion("average_exercise is not null");
            return this;
        }

        public Criteria andAverageExerciseEqualTo(BigDecimal value) {
            addCriterion("average_exercise =", value, "averageExercise");
            return this;
        }

        public Criteria andAverageExerciseNotEqualTo(BigDecimal value) {
            addCriterion("average_exercise <>", value, "averageExercise");
            return this;
        }

        public Criteria andAverageExerciseGreaterThan(BigDecimal value) {
            addCriterion("average_exercise >", value, "averageExercise");
            return this;
        }

        public Criteria andAverageExerciseGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("average_exercise >=", value, "averageExercise");
            return this;
        }

        public Criteria andAverageExerciseLessThan(BigDecimal value) {
            addCriterion("average_exercise <", value, "averageExercise");
            return this;
        }

        public Criteria andAverageExerciseLessThanOrEqualTo(BigDecimal value) {
            addCriterion("average_exercise <=", value, "averageExercise");
            return this;
        }

        public Criteria andAverageExerciseIn(List values) {
            addCriterion("average_exercise in", values, "averageExercise");
            return this;
        }

        public Criteria andAverageExerciseNotIn(List values) {
            addCriterion("average_exercise not in", values, "averageExercise");
            return this;
        }

        public Criteria andAverageExerciseBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("average_exercise between", value1, value2, "averageExercise");
            return this;
        }

        public Criteria andAverageExerciseNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("average_exercise not between", value1, value2, "averageExercise");
            return this;
        }

        public Criteria andAverageExamIsNull() {
            addCriterion("average_exam is null");
            return this;
        }

        public Criteria andAverageExamIsNotNull() {
            addCriterion("average_exam is not null");
            return this;
        }

        public Criteria andAverageExamEqualTo(BigDecimal value) {
            addCriterion("average_exam =", value, "averageExam");
            return this;
        }

        public Criteria andAverageExamNotEqualTo(BigDecimal value) {
            addCriterion("average_exam <>", value, "averageExam");
            return this;
        }

        public Criteria andAverageExamGreaterThan(BigDecimal value) {
            addCriterion("average_exam >", value, "averageExam");
            return this;
        }

        public Criteria andAverageExamGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("average_exam >=", value, "averageExam");
            return this;
        }

        public Criteria andAverageExamLessThan(BigDecimal value) {
            addCriterion("average_exam <", value, "averageExam");
            return this;
        }

        public Criteria andAverageExamLessThanOrEqualTo(BigDecimal value) {
            addCriterion("average_exam <=", value, "averageExam");
            return this;
        }

        public Criteria andAverageExamIn(List values) {
            addCriterion("average_exam in", values, "averageExam");
            return this;
        }

        public Criteria andAverageExamNotIn(List values) {
            addCriterion("average_exam not in", values, "averageExam");
            return this;
        }

        public Criteria andAverageExamBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("average_exam between", value1, value2, "averageExam");
            return this;
        }

        public Criteria andAverageExamNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("average_exam not between", value1, value2, "averageExam");
            return this;
        }

        public Criteria andManualScoreIsNull() {
            addCriterion("manual_score is null");
            return this;
        }

        public Criteria andManualScoreIsNotNull() {
            addCriterion("manual_score is not null");
            return this;
        }

        public Criteria andManualScoreEqualTo(BigDecimal value) {
            addCriterion("manual_score =", value, "manualScore");
            return this;
        }

        public Criteria andManualScoreNotEqualTo(BigDecimal value) {
            addCriterion("manual_score <>", value, "manualScore");
            return this;
        }

        public Criteria andManualScoreGreaterThan(BigDecimal value) {
            addCriterion("manual_score >", value, "manualScore");
            return this;
        }

        public Criteria andManualScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("manual_score >=", value, "manualScore");
            return this;
        }

        public Criteria andManualScoreLessThan(BigDecimal value) {
            addCriterion("manual_score <", value, "manualScore");
            return this;
        }

        public Criteria andManualScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("manual_score <=", value, "manualScore");
            return this;
        }

        public Criteria andManualScoreIn(List values) {
            addCriterion("manual_score in", values, "manualScore");
            return this;
        }

        public Criteria andManualScoreNotIn(List values) {
            addCriterion("manual_score not in", values, "manualScore");
            return this;
        }

        public Criteria andManualScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("manual_score between", value1, value2, "manualScore");
            return this;
        }

        public Criteria andManualScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("manual_score not between", value1, value2, "manualScore");
            return this;
        }

        public Criteria andChallengesDoneIsNull() {
            addCriterion("challenges_done is null");
            return this;
        }

        public Criteria andChallengesDoneIsNotNull() {
            addCriterion("challenges_done is not null");
            return this;
        }

        public Criteria andChallengesDoneEqualTo(Short value) {
            addCriterion("challenges_done =", value, "challengesDone");
            return this;
        }

        public Criteria andChallengesDoneNotEqualTo(Short value) {
            addCriterion("challenges_done <>", value, "challengesDone");
            return this;
        }

        public Criteria andChallengesDoneGreaterThan(Short value) {
            addCriterion("challenges_done >", value, "challengesDone");
            return this;
        }

        public Criteria andChallengesDoneGreaterThanOrEqualTo(Short value) {
            addCriterion("challenges_done >=", value, "challengesDone");
            return this;
        }

        public Criteria andChallengesDoneLessThan(Short value) {
            addCriterion("challenges_done <", value, "challengesDone");
            return this;
        }

        public Criteria andChallengesDoneLessThanOrEqualTo(Short value) {
            addCriterion("challenges_done <=", value, "challengesDone");
            return this;
        }

        public Criteria andChallengesDoneIn(List values) {
            addCriterion("challenges_done in", values, "challengesDone");
            return this;
        }

        public Criteria andChallengesDoneNotIn(List values) {
            addCriterion("challenges_done not in", values, "challengesDone");
            return this;
        }

        public Criteria andChallengesDoneBetween(Short value1, Short value2) {
            addCriterion("challenges_done between", value1, value2, "challengesDone");
            return this;
        }

        public Criteria andChallengesDoneNotBetween(Short value1, Short value2) {
            addCriterion("challenges_done not between", value1, value2, "challengesDone");
            return this;
        }

        public Criteria andChallengesWeightIsNull() {
            addCriterion("challenges_weight is null");
            return this;
        }

        public Criteria andChallengesWeightIsNotNull() {
            addCriterion("challenges_weight is not null");
            return this;
        }

        public Criteria andChallengesWeightEqualTo(Integer value) {
            addCriterion("challenges_weight =", value, "challengesWeight");
            return this;
        }

        public Criteria andChallengesWeightNotEqualTo(Integer value) {
            addCriterion("challenges_weight <>", value, "challengesWeight");
            return this;
        }

        public Criteria andChallengesWeightGreaterThan(Integer value) {
            addCriterion("challenges_weight >", value, "challengesWeight");
            return this;
        }

        public Criteria andChallengesWeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("challenges_weight >=", value, "challengesWeight");
            return this;
        }

        public Criteria andChallengesWeightLessThan(Integer value) {
            addCriterion("challenges_weight <", value, "challengesWeight");
            return this;
        }

        public Criteria andChallengesWeightLessThanOrEqualTo(Integer value) {
            addCriterion("challenges_weight <=", value, "challengesWeight");
            return this;
        }

        public Criteria andChallengesWeightIn(List values) {
            addCriterion("challenges_weight in", values, "challengesWeight");
            return this;
        }

        public Criteria andChallengesWeightNotIn(List values) {
            addCriterion("challenges_weight not in", values, "challengesWeight");
            return this;
        }

        public Criteria andChallengesWeightBetween(Integer value1, Integer value2) {
            addCriterion("challenges_weight between", value1, value2, "challengesWeight");
            return this;
        }

        public Criteria andChallengesWeightNotBetween(Integer value1, Integer value2) {
            addCriterion("challenges_weight not between", value1, value2, "challengesWeight");
            return this;
        }

        public Criteria andAverageChallengeIsNull() {
            addCriterion("average_challenge is null");
            return this;
        }

        public Criteria andAverageChallengeIsNotNull() {
            addCriterion("average_challenge is not null");
            return this;
        }

        public Criteria andAverageChallengeEqualTo(BigDecimal value) {
            addCriterion("average_challenge =", value, "averageChallenge");
            return this;
        }

        public Criteria andAverageChallengeNotEqualTo(BigDecimal value) {
            addCriterion("average_challenge <>", value, "averageChallenge");
            return this;
        }

        public Criteria andAverageChallengeGreaterThan(BigDecimal value) {
            addCriterion("average_challenge >", value, "averageChallenge");
            return this;
        }

        public Criteria andAverageChallengeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("average_challenge >=", value, "averageChallenge");
            return this;
        }

        public Criteria andAverageChallengeLessThan(BigDecimal value) {
            addCriterion("average_challenge <", value, "averageChallenge");
            return this;
        }

        public Criteria andAverageChallengeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("average_challenge <=", value, "averageChallenge");
            return this;
        }

        public Criteria andAverageChallengeIn(List values) {
            addCriterion("average_challenge in", values, "averageChallenge");
            return this;
        }

        public Criteria andAverageChallengeNotIn(List values) {
            addCriterion("average_challenge not in", values, "averageChallenge");
            return this;
        }

        public Criteria andAverageChallengeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("average_challenge between", value1, value2, "averageChallenge");
            return this;
        }

        public Criteria andAverageChallengeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("average_challenge not between", value1, value2, "averageChallenge");
            return this;
        }

        public Criteria andChallengesTotalIsNull() {
            addCriterion("challenges_total is null");
            return this;
        }

        public Criteria andChallengesTotalIsNotNull() {
            addCriterion("challenges_total is not null");
            return this;
        }

        public Criteria andChallengesTotalEqualTo(BigDecimal value) {
            addCriterion("challenges_total =", value, "challengesTotal");
            return this;
        }

        public Criteria andChallengesTotalNotEqualTo(BigDecimal value) {
            addCriterion("challenges_total <>", value, "challengesTotal");
            return this;
        }

        public Criteria andChallengesTotalGreaterThan(BigDecimal value) {
            addCriterion("challenges_total >", value, "challengesTotal");
            return this;
        }

        public Criteria andChallengesTotalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("challenges_total >=", value, "challengesTotal");
            return this;
        }

        public Criteria andChallengesTotalLessThan(BigDecimal value) {
            addCriterion("challenges_total <", value, "challengesTotal");
            return this;
        }

        public Criteria andChallengesTotalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("challenges_total <=", value, "challengesTotal");
            return this;
        }

        public Criteria andChallengesTotalIn(List values) {
            addCriterion("challenges_total in", values, "challengesTotal");
            return this;
        }

        public Criteria andChallengesTotalNotIn(List values) {
            addCriterion("challenges_total not in", values, "challengesTotal");
            return this;
        }

        public Criteria andChallengesTotalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("challenges_total between", value1, value2, "challengesTotal");
            return this;
        }

        public Criteria andChallengesTotalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("challenges_total not between", value1, value2, "challengesTotal");
            return this;
        }
    }
}