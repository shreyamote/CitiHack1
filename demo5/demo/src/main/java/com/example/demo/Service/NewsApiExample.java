package com.example.demo.Service;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Service
public class NewsApiExample {

    private static String[] positiveWords = {
            "good", "great", "excellent", "positive", "awesome", "superb", "fantastic","bonus",
            "amazing", "wonderful", "brilliant", "splendid", "outstanding", "exceptional",
            "marvelous", "terrific", "fabulous", "perfect", "phenomenal", "incredible",
            "profit", "growth", "successful", "strong", "innovative", "leader", "advantage",
            "beneficial", "promising", "rewarding", "prosperous", "vibrant", "dynamic",
            "uplifting", "progress", "achievement", "celebrated", "valuable", "satisfactory",
            "exciting", "gain", "opportunity", "advance", "asset", "benefit", "triumph",
            "effective", "efficient", "productive", "reliable", "stellar",
            "pleasure", "happy", "joyful", "delightful", "grateful", "content", "optimistic",
            "enthusiastic", "ecstatic", "lucky", "fortunate", "charming", "radiant",
            "refreshing", "exhilarating", "glorious", "fantasy", "splendor", "jubilant",
            "thrilled", "exuberant", "festive", "inspiring", "thriving", "victory",
            "love", "kind", "beautiful", "adorable", "kindness", "romantic", "affection",
            "passionate", "compassion", "caring", "adoration", "devotion", "tender",
            "tenderness", "embrace", "warm", "lovable", "angelic", "cherish", "admire",
            "friendship", "friendly", "companion", "bond", "unity", "loyalty", "trust",
            "respect", "support", "helpful", "encouraging", "empowering", "insightful",
            "creative", "imaginative", "artistic", "innovation", "visionary", "talented",
            "genius", "genial", "brave", "courage", "fearless", "heroic", "bold", "daring",
            "adventure", "exploration", "curious", "discovery", "knowledge", "wisdom",
            "intelligence", "intellect", "smart", "clever", "bright", "intuitive", "sage",
            "wise", "learned", "insight", "inspiring", "vision", "understanding",
            "compassionate", "kindhearted", "affectionate", "empathetic", "gentle",
            "sympathetic", "considerate", "thoughtful", "tenderhearted", "warmhearted",
            "generous", "giving", "charitable", "selfless", "altruistic", "philanthropic",
            "beneficent", "benevolent", "gracious", "humble", "modest", "unassuming",
            "gentleman", "polite", "courteous", "civil", "respectful", "dignified", "decent",
            "noble", "honorable", "ethical", "upright", "virtuous", "righteous", "just",
            "fair", "equitable", "honest", "sincere", "truthful", "trustworthy", "reliable",
            "dependable", "loyal", "faithful", "dedicated", "committed", "devoted", "steadfast",
            "perseverance", "endurance", "resilient", "tough", "strong-willed", "determined",
            "persistent", "tenacious", "patient", "tolerant", "forgiving", "compassionate",
            "sympathetic", "understanding", "empathetic", "grateful", "thankful", "appreciative",
            "blessed", "fortunate", "lucky", "privileged", "prosperous", "abundant", "successful",
            "accomplished", "victorious", "triumphant", "proud", "confident", "self-assured",
            "self-reliant", "independent", "resourceful", "capable", "competent", "skilled",
            "talented", "gifted", "clever", "intelligent", "bright", "sharp", "quick-witted",
            "astute", "perceptive", "knowledgeable", "wise", "insightful", "thoughtful", "aware",
            "conscious", "mindful", "perceptive", "attentive", "alert", "observant", "watchful",
            "vigilant", "cautious", "careful", "prudent", "wise", "sensible", "thoughtful",
            "considerate", "mindful", "reflective", "contemplative", "meditative", "introspective",
            "analytical", "logical", "rational", "reasonable", "sensible", "practical", "realistic",
            "pragmatic", "grounded", "down-to-earth", "level-headed", "calm", "composed", "serene",
            "peaceful", "tranquil", "relaxed", "untroubled", "undisturbed", "content", "comfortable",
            "satisfied", "pleased", "fulfilled", "contented", "happy", "joyful", "cheerful", "merry",
            "jovial", "jolly", "lighthearted", "carefree", "blissful", "ecstatic", "exuberant", "radiant",
            "vibrant", "dynamic", "energetic", "enthusiastic", "passionate", "fervent", "zealous",
            "ardent", "intense", "strong", "powerful", "robust", "vigorous", "sturdy", "durable",
            "resilient", "tough", "hardy", "enduring", "persevering", "patient", "persistent",
            "tenacious", "determined", "dedicated", "committed", "loyal", "faithful", "devoted",
            "sincere", "genuine", "authentic", "honest", "trustworthy", "reliable", "dependable",
            "consistent", "steadfast", "steadfast", "reliable", "trustworthy", "unwavering",
            "resolute", "firm", "strong", "steady", "stable", "solid", "secure", "confident",
            "self-assured", "assured", "composed", "calm", "collected", "cool-headed", "level-headed",
            "poised", "balanced", "centered", "grounded", "level", "even-tempered", "equanimous",
            "tranquil", "serene", "peaceful", "relaxed", "unperturbed", "unflappable", "unruffled",
            "graceful", "elegant", "sophisticated", "refined", "cultured", "polished", "urbane",
            "stylish", "tasteful", "chic", "fashionable", "trendy", "modern", "contemporary",
            "sleek", "cutting-edge", "innovative", "avant-garde", "progressive", "forward-thinking",
            "pioneering", "visionary", "creative", "imaginative", "inspired", "inventive", "artistic",
            "expressive", "artful", "masterful", "skillful", "talented", "gifted", "ingenious",
            "brilliant", "clever", "smart", "sharp", "quick", "nimble", "agile", "astute", "keen",
            "shrewd", "perceptive", "intuitive", "insightful", "wise", "sage", "erudite", "learned",
            "knowledgeable", "educated", "well-read", "intellectual", "bookish", "academic", "scholarly",
            "thoughtful", "pensive", "reflective", "contemplative", "meditative", "introspective",
            "philosophical", "idealistic", "principled", "ethical", "moral", "righteous", "upright",
            "noble", "virtuous", "honorable", "dignified", "respectable", "worthy", "admirable",
            "commendable", "exemplary", "model", "heroic", "courageous", "valiant", "gallant",
            "bold", "brave","profitability", "optimization", "diversification", "resilience", "confidence",
            "innovation", "accuracy", "efficiency", "reputation", "growth",
            "stability", "capital", "security", "compliance", "predictive",
            "opportunities", "performance", "investment", "insurance", "guarantees",
            "recovery", "improvement", "sustainability", "robustness", "solidity",
            "advancement", "reliability", "credibility", "trustworthiness", "prudence",
            "strategic", "proactive", "enhancement", "benefit", "strength",
            "adaptability", "innovation", "analytics", "insights", "governance",
            "discipline", "foresight", "resourcefulness", "precision", "prevention",
            "continuity", "assurance", "control", "oversight", "protection",
            "minimization", "monitoring", "evaluation", "forecasting", "management",
            "integration", "alignment", "optimization", "allocation", "distribution",
            "leveraging", "safeguarding", "mitigation", "prudence", "risk-adjusted",
            "capital adequacy", "creditworthiness", "credit scoring", "risk models", "credit analysis",
            "portfolio management", "credit monitoring", "risk assessment", "loan underwriting", "credit policies",
            "credit control", "regulatory compliance", "stress testing", "risk metrics", "risk appetite",
            "risk tolerance", "credit risk frameworks", "credit risk committees", "risk governance", "risk culture",
            "risk management systems", "risk reporting", "credit audit", "credit risk reviews", "credit risk dashboards",
            "credit risk indicators", "credit risk limits", "credit risk thresholds", "credit risk parameters", "credit risk strategies",
            "credit risk objectives", "credit risk goals", "credit risk targets", "credit risk plans", "credit risk programs",
            "credit risk initiatives", "credit risk projects", "credit risk policies", "credit risk procedures", "credit risk protocols",
            "credit risk processes", "credit risk standards", "credit risk guidelines", "credit risk rules", "credit risk principles",
            "credit risk practices", "credit risk frameworks", "credit risk methodologies", "credit risk techniques", "credit risk tools",
            "credit risk systems", "credit risk platforms", "credit risk applications", "credit risk solutions", "credit risk services",
            "credit risk products", "credit risk offerings", "credit risk innovations", "credit risk advancements", "credit risk developments",
            "credit risk trends", "credit risk technologies", "credit risk best practices", "credit risk benchmarks", "credit risk evaluations",
            "credit risk assessments", "credit risk audits", "credit risk reviews", "credit risk appraisals", "credit risk examinations",
            "credit risk inspections", "credit risk investigations", "credit risk checks", "credit risk analyses", "credit risk reports",
            "credit risk summaries", "credit risk overviews", "credit risk findings", "credit risk insights", "credit risk observations",
            "credit risk comments", "credit risk feedback", "credit risk recommendations", "credit risk suggestions", "credit risk proposals",
            "credit risk solutions", "credit risk remedies", "credit risk actions", "credit risk measures", "credit risk steps",
            "credit risk initiatives", "credit risk improvements", "credit risk enhancements", "credit risk innovations", "credit risk advancements",
            "credit risk developments", "credit risk trends", "credit risk opportunities", "credit risk benefits", "credit risk gains",
            "credit risk advantages", "credit risk profits", "credit risk success", "credit risk achievements", "credit risk milestones",
            "credit risk progress", "credit risk growth", "credit risk expansion", "credit risk increase", "credit risk boost",
            "credit risk rise", "credit risk uplift", "credit risk surge", "credit risk peak", "credit risk zenith",
            "credit risk apex", "credit risk climax", "credit risk pinnacle", "credit risk high", "credit risk top",
            "credit risk summit", "credit risk crest", "credit risk acme", "credit risk apogee", "credit risk zenith",
            "credit risk prominence", "credit risk eminence", "credit risk distinction", "credit risk excellence", "credit risk superiority",
            "credit risk supremacy", "credit risk preeminence", "credit risk prestige", "credit risk honor", "credit risk dignity",
            "credit risk respect", "credit risk esteem", "credit risk regard", "credit risk acclaim", "credit risk recognition",
            "credit risk acknowledgement", "credit risk commendation", "credit risk admiration", "credit risk appreciation", "credit risk gratitude",
            "credit risk thankfulness", "credit risk acknowledgment", "credit risk appreciation", "credit risk recognition", "credit risk value",
            "credit risk worth", "credit risk merit", "credit risk benefit", "credit risk advantage", "credit risk profit",
            "credit risk gain", "credit risk yield", "credit risk return", "credit risk revenue", "credit risk income",
            "credit risk earning", "credit risk dividend", "credit risk interest", "credit risk royalty", "credit risk surplus",
            "credit risk bonus", "credit risk perk", "credit risk advantage", "credit risk benefit", "credit risk profit",
            "credit risk gain", "credit risk yield", "credit risk return", "credit risk revenue", "credit risk income",
            "credit risk earning", "credit risk dividend", "credit risk interest", "credit risk royalty", "credit risk surplus",
            "credit risk bonus", "credit risk perk", "credit risk fortune", "credit risk wealth", "credit risk riches",
            "credit risk treasure", "credit risk assets", "credit risk holdings", "credit risk possessions", "credit risk property",
            "credit risk capital", "credit risk funds", "credit risk reserves", "credit risk finances", "credit risk wealth",
            "credit risk fortune", "credit risk riches", "credit risk treasure", "credit risk assets", "credit risk holdings",
            "credit risk possessions", "credit risk property", "credit risk capital", "credit risk funds", "credit risk reserves",
            "credit risk finances", "credit risk wealth", "credit risk fortune", "credit risk riches", "credit risk treasure",
            "credit risk assets", "credit risk holdings", "credit risk possessions", "credit risk property", "credit risk capital",
            "credit risk funds", "credit risk reserves", "credit risk finances", "credit risk wealth", "credit risk fortune",
            "credit risk riches", "credit risk treasure", "credit risk assets", "credit risk holdings", "credit risk possessions",
            "credit risk property", "credit risk capital", "credit risk funds", "credit risk reserves", "credit risk finances",
            "credit risk wealth", "credit risk fortune", "credit risk riches", "credit risk treasure", "credit risk assets",
            "credit risk holdings", "credit risk possessions", "credit risk property", "credit risk capital", "credit risk funds",
            "credit risk reserves", "credit risk finances", "credit risk wealth", "credit risk fortune", "credit risk riches",
            "credit risk treasure", "credit risk assets", "credit risk holdings", "credit risk possessions", "credit risk property",
            "credit risk capital", "credit risk funds", "credit risk reserves", "credit risk finances", "credit risk wealth",
            "credit risk fortune", "credit risk riches", "credit risk treasure", "credit risk assets", "credit risk holdings",
            "credit risk possessions", "credit risk property", "credit risk capital", "credit risk funds", "credit risk reserves",
            "credit risk finances", "credit risk wealth", "credit risk fortune", "credit risk riches", "credit risk treasure",
            "credit risk assets", "credit risk holdings", "credit risk possessions", "credit risk property", "credit risk capital",
            "credit risk funds", "credit risk reserves", "credit risk finances", "credit risk wealth", "credit risk fortune",
            "credit risk riches", "credit risk treasure", "credit risk assets", "credit risk holdings", "credit risk possessions",
            "credit risk property", "credit risk capital", "credit risk funds", "credit risk reserves", "credit risk finances"
            // Add more positive words related to business
    };

    private static String[] negativeWords = {
            "bad", "terrible", "poor", "negative", "awful", "horrible", "weak",
            "loss", "decline", "struggle", "failure", "bankruptcy", "layoffs", "crisis",
            "downturn", "collapse", "risk", "scandal", "fraud", "dispute",
            "challenging", "difficult", "problematic", "disappointing", "dismal", "painful",
            "setback", "unfortunate", "unfavorable", "concern", "weakness", "detrimental",
            "trouble", "debt", "downfall", "unstable", "hazard", "ineffective", "inefficient",
            "unreliable", "unsatisfactory", "inferior", "damaging", "threat", "tough", "strife",
            "turmoil", "crash", "weak", "flop", "shaky", "unprofitable", "volatile", "fragile",
            "depressed", "desperate", "bleak", "gloomy", "dreary", "melancholy", "dismal",
            "grief", "grief", "misery", "woe", "anguish", "despair", "sadness", "depression",
            "unhappy", "unfortunate", "disappointment", "disillusionment", "frustration",
            "exasperation", "irritation", "annoyance", "botheration", "displeasure", "vexation",
            "irritability", "discontent", "disgruntlement", "discontentment", "resentment",
            "bitterness", "envy", "jealousy", "resentment", "animosity", "hatred", "malice",
            "spite", "weakness", "ill will", "hostility", "antagonism", "unfriendliness", "discord",
            "conflict", "strife", "tension", "disagreement", "argument", "controversy",
            "dispute", "misunderstanding", "clash", "fracas", "brawl", "scuffle", "fight",
            "quarrel", "dispute", "contention", "disaccord", "division", "rift", "separation",
            "alienation", "estrangement", "discordance", "dissension", "split", "chasm",
            "schism", "divorce", "rift", "breach", "breakup", "falling out", "parting",
            "estrangement", "rejection", "neglect", "abandonment", "isolation", "solitude",
            "seclusion", "exclusion", "ostracism", "ostracization", "banishment", "shunning",
            "outcast", "rejection", "cold shoulder", "blackballing", "ignorance", "neglect",
            "negligence", "disregard", "disrespect", "insolence", "contempt", "disdain",
            "snubbing", "rebuff", "rejection", "slight", "offense", "affront", "insult",
            "indignity", "humiliation", "embarrassment", "shame", "disgrace", "dishonor",
            "mortification", "abasement", "chagrin", "regret", "guilt", "remorse", "penitence",
            "shame", "contrition", "humiliation", "mortification", "blunder", "error", "mistake",
            "fault", "slip-up", "miscalculation", "mishap", "accident", "failure", "defeat",
            "misfortune", "misery", "calamity", "disaster", "catastrophe", "tragedy", "adversity",
            "mishap", "mischance", "setback", "fiasco", "debacle", "bungle", "botch", "mess-up",
            "ruin", "wreck", "blunder", "disaster", "flop", "bomb", "dud", "catastrophe",
            "collapse", "ruin", "bankruptcy", "misery", "fiasco", "mess", "downfall", "debacle",
            "disaster", "wreck", "defeat", "loss", "meltdown", "bust", "lemon", "disaster",
            "flop", "blow", "debacle", "downfall", "fizzle", "bust", "misstep", "debacle",
            "fiasco", "setback", "downturn", "decline", "recession", "slump", "depression",
            "bust", "failure", "loss", "default", "insolvency", "collapse", "closure",
            "bankruptcy", "liquidation", "shutdown", "discontinuation", "failure", "decline",
            "recession", "depression", "slump", "downturn", "collapse", "crash", "crunch",
            "panic", "meltdown", "failure", "collapse", "defeat", "disappointment", "letdown",
            "disaster", "trouble", "ruin", "blow", "debacle", "fiasco", "downfall", "bust",
            "flop", "failure", "flop", "collapse", "ruin", "disaster", "wreck", "blunder",
            "fiasco", "botch", "mess", "disaster", "failure", "bust", "misfortune", "tragedy",
            "setback", "mishap", "accident", "reverse", "misery", "calamity", "catastrophe",
            "adversity", "hardship", "difficulty", "trouble", "tribulation", "worry", "stress",
            "anxiety", "pressure", "strain", "tension", "tenseness", "nervousness", "restlessness",
            "edginess", "jitters", "apprehension", "fear", "dread", "alarm", "panic", "unease",
            "disquiet", "discomfort", "malaise", "uneasiness", "agitation", "trepidation",
            "foreboding", "perturbation", "consternation", "fright", "terror", "horror",
            "panic", "dismay", "shock", "awe", "astonishment", "stupefaction", "bewilderment",
            "confusion", "perplexity", "bafflement", "mystification", "puzzlement", "doubt",
            "uncertainty", "confusion", "perplexity", "bewilderment", "mystification",
            "puzzlement", "uncertainty", "dilemma", "quandary", "predicament", "plight",
            "pickle", "difficulty", "jam", "trouble", "problem", "fix", "bind", "mess", "muddle",
            "entanglement", "snarl", "snag", "quandary", "conundrum", "puzzle", "riddle",
            "mystery", "enigma", "challenge", "obstacle", "hurdle", "barrier", "impediment",
            "stumbling block", "setback", "delay", "hindrance", "obstruction", "difficulty",
            "problem", "complication", "drawback", "disadvantage", "downside", "shortcoming",
            "weakness", "flaw", "defect", "fault", "bug", "glitch", "inconsistency", "limitation",
            "restriction", "constraint", "compromise", "setback", "defeat", "letdown", "failure",
            "disappointment", "misfortune", "mishap", "accident", "calamity", "adversity",
            "hardship", "trouble", "issue", "problem", "difficulty", "obstacle", "hiccup",
            "complication", "snag", "glitch", "snafu", "setback", "delay", "hindrance", "barrier",
            "impediment", "stumbling block", "nuisance", "irritation", "annoyance", "bother",
            "inconvenience", "disruption", "interruption", "disturbance", "disorder", "turmoil",
            "chaos", "confusion", "mayhem", "pandemonium", "havoc", "bedlam", "tumult", "uproar", "die",
            "defaults", "losses", "insolvency", "non-performing", "scrutiny",
            "requirements", "downturns", "instability", "concentration", "risk",
            "confidence", "damage", "trust", "tightening", "slowdown",
            "strain", "reserves", "liquidity", "crises", "obligations",
            "delinquency", "bankruptcy", "foreclosure", "repossession", "write-off",
            "impairment", "provisions", "underperformance", "deterioration", "decline",
            "credit squeeze", "credit crunch", "exposure", "vulnerability", "uncertainty",
            "recession", "depression", "turmoil", "volatility", "stress",
            "fraud", "mismanagement", "negligence", "default", "delinquent",
            "foreclosure", "repossession", "bankruptcy", "insolvency", "write-down",
            "write-off", "debt", "non-compliance", "regulation", "sanctions",
            "penalties", "fines", "losses", "charges", "provisions",
            "impairments", "overdue", "arrears", "unpaid", "outstanding",
            "collection", "enforcement", "litigation", "dispute", "conflict",
            "disagreement", "challenge", "difficulty", "hardship", "distress",
            "problem", "issue", "complication", "setback", "obstacle",
            "barrier", "hurdle", "limitation", "restriction", "constraint",
            "bottleneck", "deadlock", "stalemate", "gridlock", "impasse",
            "crisis", "catastrophe", "disaster", "calamity", "emergency",
            "predicament", "dilemma", "quandary", "trouble", "adversity",
            "misfortune", "hardship", "distress", "struggle", "difficulty",
            "burden", "strain", "stress", "pressure", "tension",
            "anxiety", "worry", "concern", "fear", "apprehension",
            "alarm", "panic", "shock", "surprise", "disbelief",
            "suspense", "uncertainty", "doubt", "confusion", "bewilderment",
            "perplexity", "puzzle", "mystery", "enigma", "conundrum",
            "riddle", "paradox", "contradiction", "inconsistency", "ambiguity",
            "obscurity", "vagueness", "unclearness", "haze", "blur",
            "smoke", "fog", "mist", "smog", "cloud",
            "dust", "pollution", "contamination", "toxicity", "hazard",
            "danger", "peril", "risk", "threat", "menace",
            "jeopardy", "pitfall", "snare", "trap", "catch",
            "entanglement", "complication", "difficulty", "problem", "issue",
            "challenge", "obstacle", "barrier", "hindrance", "block",
            "impediment", "restriction", "limitation", "constraint", "obstruction",
            "friction", "tension", "conflict", "discord", "dispute",
            "disagreement", "argument", "quarrel", "fight", "battle",
            "war", "combat", "clash", "struggle", "tussle",
            "wrestle", "grapple", "scuffle", "brawl", "fracas",
            "melee", "skirmish", "raid", "invasion", "assault",
            "attack", "offensive", "onslaught", "ambush", "strike",
            "blow", "hit", "punch", "kick", "slap",
            "knock", "thump", "smash", "crash", "bang",
            "boom", "blast", "explosion", "eruption", "outburst",
            "flurry", "rush", "surge", "wave", "tide",
            "flood", "torrent", "deluge", "inundation", "overflow",
            "spill", "leak", "seep", "drip", "drop",
            "trickle", "stream", "flow", "current", "ripple",
            "wave", "swell", "surge", "rise", "fall",
            "drop", "decline", "decrease", "diminish", "reduce",
            "cut", "slash", "trim", "shrink", "contract",
            "compress", "squeeze", "squash", "crush", "flatten",
            "press", "compact", "pack", "stuff", "jam",
            "crowd", "cram", "squeeze", "squash", "compress",
            "condense", "collapse", "deflate", "shrink", "wither",
            "wilt", "fade", "decline", "diminish", "reduce",
            "lessen", "decrease", "shrink", "contract", "compress",
            "squeeze", "condense", "compact", "crush", "flatten",
            "squash", "press", "compress", "pack", "stuff",
            "jam", "crowd", "cram", "squeeze", "squash",
            "compress", "condense", "compact", "crush", "flatten",
            "press", "compress", "pack", "stuff", "jam",
            "crowd", "cram", "squeeze", "squash", "compress",
            "condense", "compact", "crush", "flatten", "press",
            "compact", "compress", "squeeze", "crush", "flatten",
            "press", "compact", "compress", "squeeze", "crush",
            "flatten", "press", "compact", "compress", "squeeze",
            "crush", "flatten", "press", "compact", "compress"
            // Add more negative words related to business
    };

    private static String[] neutralWords = {
            "the", "is", "are", "was", "were", "a", "an", "and", "or", "but", "to", "of", "in", "on", "at", "with",
            "assessment", "management", "mitigation", "regulation", "framework",
            "inherent", "balance", "safety", "diversification", "potential",
            "default", "health", "strategies", "factors", "conditions",
            "trends", "testing", "evaluation", "exposure", "technology",
            "policies", "procedures", "protocols", "standards", "guidelines",
            "rules", "principles", "practices", "methodologies", "techniques",
            "tools", "systems", "platforms", "applications", "solutions",
            "com/example/final1/services", "products", "offerings", "innovations", "advancements",
            "developments", "best practices", "benchmarks", "evaluations", "assessments",
            "audits", "reviews", "appraisals", "examinations", "inspections",
            "investigations", "checks", "analyses", "reports", "summaries",
            "overviews", "findings", "insights", "observations", "comments",
            "feedback", "recommendations", "suggestions", "proposals", "remedies",
            "actions", "measures", "steps", "initiatives", "improvements",
            "enhancements", "developments", "trends", "opportunities", "benefits",
            "gains", "advantages", "profits", "success", "achievements",
            "milestones", "progress", "growth", "expansion", "increase",
            "boost", "rise", "uplift", "surge", "peak",
            "zenith", "apex", "climax", "pinnacle", "high",
            "top", "summit", "crest", "acme", "apogee",
            "prominence", "eminence", "distinction", "excellence", "superiority",
            "supremacy", "preeminence", "prestige", "honor", "dignity",
            "respect", "esteem", "regard", "acclaim", "recognition",
            "acknowledgement", "commendation", "admiration", "appreciation", "gratitude",
            "thankfulness", "acknowledgment", "value", "worth", "merit",
            "benefit", "advantage", "yield", "return", "revenue",
            "income", "earning", "dividend", "interest", "royalty",
            "surplus", "bonus", "perk", "fortune", "wealth",
            "riches", "treasure", "assets", "holdings", "possessions",
            "property", "capital", "funds", "reserves", "finances",
            "help", "support", "assistance", "aid", "backing",
            "encouragement", "promotion", "facilitation", "advancement", "boost",
            "stimulus", "encouragement", "impetus", "drive", "motivation",
            "urge", "incentive", "inspiration", "persuasion", "inducement",
            "influence", "guidance", "direction", "leadership", "control",
            "command", "supervision", "oversight", "management", "administration",
            "coordination", "regulation", "organization", "arrangement", "systematization",
            "structuring", "order", "system", "mechanism", "apparatus",
            "instrument", "tool", "device", "implement", "equipment",
            "gear", "machine", "contrivance", "gadget", "utensil",
            "instrumentality", "medium", "agency", "channel", "organ",
            "institution", "body", "system", "structure", "organism",
            "entity", "organization", "scheme", "program", "plan",
            "project", "undertaking", "enterprise", "operation", "venture",
            "activity", "action", "effort", "initiative", "move",
            "step", "measure", "procedure", "process", "policy",
            "strategy", "approach", "tactic", "method", "technique",
            "methodology", "practice", "principle", "rule", "regulation",
            "law", "standard", "norm", "guide", "protocol",
            "procedure", "custom", "tradition", "ritual", "convention",
            "ceremony", "formality", "rule", "order", "law",
            "regulation", "enactment", "statute", "decree", "mandate",
            "edict", "command", "directive", "injunction", "instruction",
            "guideline", "guidance", "advice", "counsel", "direction",
            "recommendation", "caveat", "warning", "tip", "hint",
            "clue", "suggestion", "admonition", "notification", "information",
            "feedback", "input", "comment", "opinion", "view",
            "perspective", "assessment", "evaluation", "appraisal", "analysis",
            "study", "examination", "review", "audit", "scrutiny",
            "inspection", "survey", "check", "test", "experiment",
            "trial", "assessment", "evaluation", "appraisal", "analysis",
            "study", "examination", "review", "audit", "scrutiny",
            "inspection", "survey", "check", "test", "experiment",
            "trial", "evaluation", "assessment", "appraisal", "analysis",
            "study", "examination", "review", "audit", "scrutiny",
            "inspection", "survey", "check", "test", "experiment",
            "trial", "evaluation", "assessment", "appraisal", "analysis",
            "study", "examination", "review", "audit", "scrutiny",
            "inspection", "survey", "check", "test", "experiment",
            "trial", "evaluation", "assessment", "appraisal", "analysis",
            "study", "examination", "review", "audit", "scrutiny",
            "inspection", "survey", "check", "test", "experiment",
            "trial", "evaluation", "assessment", "appraisal", "analysis",
            "study", "examination", "review", "audit", "scrutiny",
            "inspection", "survey", "check", "test", "experiment",
            "trial", "evaluation", "assessment", "appraisal", "analysis",
            "study", "examination", "review", "audit", "scrutiny",
            "inspection", "survey", "check", "test", "experiment",
            "trial", "evaluation", "assessment", "appraisal", "analysis",
            "study", "examination", "review", "audit", "scrutiny",
            "inspection", "survey", "check", "test", "experiment",
            "trial", "evaluation", "assessment", "appraisal", "analysis",
            "study", "examination", "review", "audit", "scrutiny",
            "inspection", "survey", "check", "test", "experiment",
            "trial", "evaluation", "assessment", "appraisal", "analysis",
            "study", "examination", "review", "audit", "scrutiny",
            "inspection", "survey", "check", "test", "experiment",
            "trial", "evaluation", "assessment", "appraisal", "analysis",
            "study", "examination", "review", "audit", "scrutiny",
            "inspection", "survey", "check", "test", "experiment",
            "trial", "evaluation", "assessment", "appraisal", "analysis"
            // Add more as needed
    };
    public List<Map<String, String>> getNews(@RequestParam(name = "query", required = false) String query) {

        List<Map<String, String>> result = new ArrayList<>();

        try {
            URL apiUrl = new URL("https://newsapi.org/v2/everything?q=" + query + " +&from=2024-07-03&sortBy=publishedAt&apiKey=28d723f06f2543dba49621a8a06b5e28");
            HttpURLConnection conn = (HttpURLConnection) apiUrl.openConnection();
            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();

            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JSONObject jsonResponse = new JSONObject(response.toString());
                JSONArray articles = jsonResponse.getJSONArray("articles");

                for (int i = 0; i < articles.length(); i++) {
                    JSONObject article = articles.getJSONObject(i);
                    String title = article.getString("title");
                    double score = calculateSentimentScore(title);

                    System.out.println("Title: " + title);
                    System.out.println("Sentiment Score: " + score);

                    Map<String, String> articleData = new HashMap<>();
                    articleData.put("title", title);
                    articleData.put("sentimentScore", String.valueOf(score));
                    result.add(articleData);
                }
            } else {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Error fetching news: " + responseCode + " - " + conn.getResponseMessage());
                result.add(error);
            }
            conn.disconnect();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static double calculateSentimentScore(String text) {
        String[] words = text.split("\\s+");
        int sentimentScore = 0;
        for (String word : words) {
            word = word.toLowerCase().replaceAll("[^a-zA-Z]", "");
            if (Arrays.asList(positiveWords).contains(word)) {
                sentimentScore++;
            } else if (Arrays.asList(negativeWords).contains(word)) {
                sentimentScore--;
            }
        }

        String sentimentLevel;
        if (sentimentScore > 2) {
            sentimentLevel = "Very positive";
        } else if (sentimentScore > 0) {
            sentimentLevel = "Positive";
        } else if (sentimentScore == 0) {
            sentimentLevel = "Neutral";
        } else if (sentimentScore >= -2) {
            sentimentLevel = "Negative";
        } else {
            sentimentLevel = "Very negative";
        }

        System.out.println("Text: " + text);
        System.out.println("Sentiment Score: " + sentimentScore + " " + sentimentLevel);
        return sentimentScore;
    }
}