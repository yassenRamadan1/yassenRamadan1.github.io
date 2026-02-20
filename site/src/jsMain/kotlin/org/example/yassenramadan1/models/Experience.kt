package org.example.yassenramadan1.models

import org.example.yassenramadan1.util.Constants.listOfJobs


enum class Experience(
    val number: String,
    val jobPosition: String,
    val description: String,
    val company: String,
    val from: String,
    val to: String,
    val active: Boolean = false,
    val jobPositionKey: String,
    val descriptionKey: String,
    val companyKey: String,
    val fromKey: String,
    val toKey: String
) {
    First(
        number = "01",
        jobPosition = "Mobile App Developer",
        description = listOfJobs.first(),
        company = "freelancing",
        from = "November 2024",
        to = "NOW",
        active = true,
        jobPositionKey = "exp_mobile_app_developer",
        descriptionKey = "exp_desc_1",
        companyKey = "exp_company_freelancing",
        fromKey = "exp_date_november_2024",
        toKey = "exp_date_now"
    ),
    Second(
        number = "02",
        jobPosition = "Flutter Developer",
        description = listOfJobs[1],
        company = "Ebda3 Tech",
        from = "May 2025",
        to = "NOW",
        active = true,
        jobPositionKey = "exp_flutter_developer",
        descriptionKey = "exp_desc_2",
        companyKey = "exp_company_ebda3",
        fromKey = "exp_date_may_2025",
        toKey = "exp_date_now"
    ),
    Third(
        number = "03",
        jobPosition = "Android App Development Trainee",
        description = listOfJobs[2],
        company = "The chance bootcamp",
        from = " July 2023",
        to = "February 2023",
        jobPositionKey = "exp_android_trainee",
        descriptionKey = "exp_desc_3",
        companyKey = "exp_company_chance",
        fromKey = "exp_date_july_2023",
        toKey = "exp_date_february_2023"
    ),
    Fourth(
        number = "04",
        jobPosition = "Front-end and cross-platform development trainee",
        description = listOfJobs[3],
        company = "ITI",
        from = " Feb 2025",
        to = "Aug 2025",
        jobPositionKey = "exp_frontend_trainee",
        descriptionKey = "exp_desc_4",
        companyKey = "exp_company_iti",
        fromKey = "exp_date_feb_2025",
        toKey = "exp_date_aug_2025"
    ),
    Fifth(
        number = "05",
        jobPosition = "Android and Cross-platform development trainee",
        description = listOfJobs[4],
        company = "DEPI",
        from = "Apr 2024",
        to = "Oct 2024",
        jobPositionKey = "exp_android_cross_trainee",
        descriptionKey = "exp_desc_5",
        companyKey = "exp_company_depi",
        fromKey = "exp_date_apr_2024",
        toKey = "exp_date_oct_2024"
    );

}
