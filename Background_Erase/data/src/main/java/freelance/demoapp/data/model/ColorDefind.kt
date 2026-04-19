package freelance.demoapp.data.model

enum class ColorDefine (
    val nameColor : String,
    val hex : String,
    val explain : String
) {
    // --- CHI TIÊU (EXPENSES) - TOP 10 ---
    CORAL_RED("Coral Red", "#FF8A80", "Chi tiêu: Ăn uống - Nổi bật nhất"),
    SOFT_ROSE("Soft Rose", "#F48FB1", "Chi tiêu: Mua sắm - Tinh tế"),
    LAVENDER("Lavender", "#CE93D8", "Chi tiêu: Giải trí - Hiện đại"),
    DEEP_PURPLE("Deep Purple", "#B39DDB", "Chi tiêu: Học tập - Trí tuệ"),
    INDIGO("Indigo", "#9FA8DA", "Chi tiêu: Hóa đơn - Tin cậy"),
    SKY_BLUE("Sky Blue", "#90CAF9", "Chi tiêu: Di chuyển - Tự do"),
    LIGHT_CYAN("Light Cyan", "#81D4FA", "Chi tiêu: Sức khỏe - Tươi mát"),
    TEAL("Teal", "#80CBC4", "Chi tiêu: Bảo hiểm - An toàn"),
    AMBER("Amber", "#FFE082", "Chi tiêu: Giao lưu - Năng lượng"),
    BLUE_GREY("Blue Grey", "#90A4AE", "Chi tiêu: Khác - Trung tính"),

    // --- CHI TIÊU BỔ TRỢ ---
    EXPENSE_11("Red Light", "#EF9A9A", "Chi tiêu: Nhạt hơn Red"),
    EXPENSE_12("Rose Dark", "#F06292", "Chi tiêu: Đậm hơn Rose"),
    EXPENSE_13("Lavender Dark", "#BA68C8", "Chi tiêu: Đậm hơn Lavender"),
    EXPENSE_14("Violet Light", "#9575CD", "Chi tiêu: Violet nhẹ"),
    EXPENSE_15("Blue Indigo", "#7986CB", "Chi tiêu: Sắc xanh Indigo"),
    EXPENSE_16("Ocean Blue", "#64B5F6", "Chi tiêu: Sắc xanh đại dương"),
    EXPENSE_17("Deep Sky", "#4FC3F7", "Chi tiêu: Xanh da trời đậm"),
    EXPENSE_18("Turquoise", "#4DD0E1", "Chi tiêu: Màu ngọc lam"),
    EXPENSE_19("Dark Teal", "#4DB6AC", "Chi tiêu: Màu xanh mòng két đậm"),
    EXPENSE_20("Soft Green", "#81C784", "Chi tiêu: Xanh lá nhẹ"),
    EXPENSE_21("Light Green", "#AED581", "Chi tiêu: Xanh lá sáng"),
    EXPENSE_22("Lime", "#DCE775", "Chi tiêu: Màu chanh"),
    EXPENSE_23("Yellow", "#FFF176", "Chi tiêu: Màu vàng"),
    EXPENSE_24("Golden", "#FFD54F", "Chi tiêu: Màu vàng kim"),
    EXPENSE_25("Orange", "#FFB74D", "Chi tiêu: Màu cam"),
    EXPENSE_26("Deep Orange", "#FF8A65", "Chi tiêu: Màu cam đậm"),
    EXPENSE_27("Brown", "#A1887F", "Chi tiêu: Màu nâu"),
    EXPENSE_28("Light Grey", "#BDBDBD", "Chi tiêu: Xám sáng"),
    EXPENSE_29("Dark Grey Blue", "#78909C", "Chi tiêu: Xám xanh đậm"),
    EXPENSE_30("Slate", "#546E7A", "Chi tiêu: Màu phiến thạch"),

    // --- THU NHẬP (INCOME) - TOP 5 ---
    EMERALD_GREEN("Emerald Green", "#66BB6A", "Thu nhập: Lương - Tươi tắn"),
    SEAFOAM_TEAL("Seafoam Teal", "#26A69A", "Thu nhập: Lãi tiết kiệm - Mát mắt"),
    SUN_GOLD("Sun Gold", "#FFCA28", "Thu nhập: Thưởng - Sang trọng"),
    OCEAN_CYAN("Ocean Cyan", "#00ACC1", "Thu nhập: Kinh doanh - Năng động"),
    LIME_GREEN("Lime Green", "#9CCC65", "Thu nhập: Bán đồ cũ - Trẻ trung"),

    // --- THU NHẬP BỔ TRỢ ---
    INCOME_06("Mint", "#A5D6A7", "Thu nhập: Khoản thu nhỏ"),
    INCOME_07("Deep Green", "#43A047", "Thu nhập: Đầu tư sinh lời"),
    INCOME_08("Bright Orange", "#FFA726", "Thu nhập: Quà tặng"),
    INCOME_09("Light Blue Green", "#80DEEA", "Thu nhập: Thu nhập thụ động"),
    INCOME_10("Pale Olive", "#C0CA33", "Thu nhập: Các khoản thu khác");
}