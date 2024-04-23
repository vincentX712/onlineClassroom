
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_task
-- ----------------------------
DROP TABLE IF EXISTS `t_task`;
CREATE TABLE `t_task` (
                          `id` varchar(255) NOT NULL,
                          `description` varchar(255) DEFAULT NULL,
                          `name` varchar(255) DEFAULT NULL,
                          `step_number` int DEFAULT NULL,
                          `step_config` mediumtext,
                          `answer` mediumtext,
                          `state` int DEFAULT NULL,
                          `create_time` timestamp NULL DEFAULT NULL,
                          `update_time` timestamp NULL DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_task
-- ----------------------------
BEGIN;
INSERT INTO `t_task` (`id`, `description`, `name`, `step_number`, `step_config`, `answer`, `state`, `create_time`, `update_time`) VALUES ('240f207a6c0d4fce90ff5b6be25f508b', '任务1描述', '任务1', 3, NULL, '[{\"cfg\":\"#\\ndiffserv domain default\\n#\\ndrop-profile default\\n#\\n authentication-scheme default\\n authorization-scheme default\\n accounting-scheme default\\n domain default \\n domain default_admin \\n local-user admin password simple admin\\n local-user admin service-type http\\n#\",\"num\":\"step1\"},{\"cfg\":\"#\\ndiffserv domain default\\n#\\ndrop-profile default\\n#\\n authentication-scheme default\\n authorization-scheme default\\n accounting-scheme default\\n domain default \\n domain default_admin \\n local-user admin password simple admin\\n local-user admin service-type http\\n#\",\"num\":\"step2\"},{\"cfg\":\"#\\ndiffserv domain default\\n#\\ndrop-profile default\\n#\\n authentication-scheme default\\n authorization-scheme default\\n accounting-scheme default\\n domain default \\n domain default_admin \\n local-user admin password simple admin\\n local-user admin service-type http\\n#\",\"num\":\"step3\"}]', 0, '2024-04-16 15:45:00', '2024-04-16 15:45:00');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;